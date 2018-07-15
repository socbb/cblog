package cn.socbb.core.bean.system;

import java.io.Serializable;

/**
 * Created by cbb on 2018/7/15.
 */
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleId;

    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
