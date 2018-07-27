package cn.socbb.core.directive;

import cn.socbb.common.enums.MenuTypeEnum;
import cn.socbb.common.freemarker.Freemarkers;
import cn.socbb.core.bean.system.Menu;
import cn.socbb.core.service.system.MenuService;
import cn.socbb.core.service.system.RoleService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cbb on 2018/7/25.
 */
@Component
public class SystemDirective implements TemplateDirectiveModel {

    private static final String METHOD_KEY = "method";

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        if (map.containsKey(METHOD_KEY)) {
            String method = Freemarkers.getString(map, METHOD_KEY);
            int pageSize = 10;
            if (map.containsKey("pageSize")) {
                String pageSizeStr = map.get("pageSize").toString();
                pageSize = Integer.parseInt(pageSizeStr);
            }
            switch (method) {
                case "roles":
                    environment.setVariable("roles", builder.build().wrap(roleService.findByRole(null)));
                    break;
                case "menus_home":
                    Long userId = Freemarkers.getLong(map, "userId");
                    environment.setVariable("menus", builder.build().wrap(menuService.homeMenuTree(userId)));
                    break;
                case "menus":
                    Menu menu = new Menu();
                    menu.setType(Freemarkers.getInteger(map, "type"));
                    environment.setVariable("menus", builder.build().wrap(menuService.findByMenuTree(menu)));
                    break;
                default:
                    break;
            }
            templateDirectiveBody.render(environment.getOut());
        }
    }
}
