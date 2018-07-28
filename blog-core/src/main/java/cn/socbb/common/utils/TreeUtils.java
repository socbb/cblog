package cn.socbb.common.utils;

import cn.socbb.common.enums.BooleanEnmu;
import cn.socbb.core.bean.system.Menu;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cbb on 2018/7/26.
 */
public class TreeUtils {

    public static List<Menu> getTree(List<Menu> menus) {
        List<Menu> result = new ArrayList<>();
        List<Menu> treeRoot = getTreeRoot(menus);
        treeRoot.forEach(root -> result.add(getMenu(menus, root)));
        return result;
    }

    /**
     * 递归过获取无限级子节点
     *
     * @param menus
     * @param menu
     * @return
     */
    private static Menu getMenu(List<Menu> menus, Menu menu) {
        List<Menu> childList = getChildList(menus, menu);
        if (CollectionUtils.isNotEmpty(childList)) {
            childList.forEach(child -> menu.getChildren().add(getMenu(childList, child)));
        }
        return menu;
    }

    /**
     * 获取所有顶级节点
     *
     * @param menus
     * @return
     */
    private static List<Menu> getTreeRoot(List<Menu> menus) {
        return menus.stream().filter(menu -> menu.getRoot() == BooleanEnmu.YES.getCode()).collect(Collectors.toList());
    }

    /**
     * 得到子节点列表
     */
    private static List<Menu> getChildList(List<Menu> menus, Menu menu) {
        return menus.stream().filter(menu_ -> menu_.getParentId() == null ? false : menu_.getParentId().longValue() == menu.getId().longValue()).collect(Collectors.toList());
    }

}
