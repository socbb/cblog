package cn.socbb.core.controller;

import cn.socbb.common.support.PageResult;
import cn.socbb.common.support.Response;
import cn.socbb.common.utils.PasswordUtils;
import cn.socbb.common.utils.ResultUtils;
import cn.socbb.common.utils.StringUtils;
import cn.socbb.core.bean.system.Menu;
import cn.socbb.core.bean.system.User;
import cn.socbb.core.service.system.MenuService;
import cn.socbb.core.service.system.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cbb on 2018/7/23.
 */
@RestController
@RequestMapping("/menu")
public class RestMenuController {

    @Autowired
    private MenuService menuService;

    @RequiresPermissions("menus")
    @PostMapping("/list")
    public PageResult list(Menu menu){
        List<Menu> list = menuService.findByMenu(menu);
        PageInfo<Menu> pageInfo = new PageInfo<>(list);
        return ResultUtils.tablePage(pageInfo);
    }

    @GetMapping("/get/{id}")
    public Response get(@PathVariable Long id) {
        return Response.success(menuService.findById(id));
    }

    @RequiresPermissions(value = {"menu:add", "menu:edit"}, logical = Logical.OR)
    @PostMapping(value = "/save")
    public Response save(Menu menu) {
        if (menu.getId() == null) {
            try {
                menuService.save(menu);
                return Response.success();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error("保存失败！");
            }
        } else {
            try {
                menuService.update(menu);
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error("保存失败！");
            }
            return Response.success();
        }
    }

    @RequiresPermissions("menu:delete")
    @PostMapping(value = "/delete")
    public Response delete(@RequestParam("ids[]") Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return Response.error("请至少选择一条记录");
        }
        menuService.delete(ids);
        return Response.success("成功删除 [" + ids.length + "] 条记录");
    }
}
