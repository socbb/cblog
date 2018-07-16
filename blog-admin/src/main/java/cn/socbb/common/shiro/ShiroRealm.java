package cn.socbb.common.shiro;

import cn.socbb.common.enums.UserStatusEnum;
import cn.socbb.common.utils.SessionUtil;
import cn.socbb.core.bean.system.Menu;
import cn.socbb.core.bean.system.Role;
import cn.socbb.core.bean.system.User;
import cn.socbb.core.service.system.MenuService;
import cn.socbb.core.service.system.RoleService;
import cn.socbb.core.service.system.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cbb on 2018/7/15.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (user.getStatus() != null && UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedAccountException("帐号已被锁定，禁止登录！");
        }

        // principal参数使用用户Id，方便动态刷新用户权限
        return new SimpleAuthenticationInfo(user.getId(),user.getPassword(),ByteSource.Util.bytes(username),getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();

        // 赋予角色
        List<Role> roleList = roleService.findRolesByUserId(userId);
        for (Role role : roleList) {
            info.addRole(role.getName());
        }

        User user = SessionUtil.getUser();
        if(null == user){
            return info;
        }

        // 赋予权限
        List<Menu> menuList = menuService.findByUserId(userId);
        if (!CollectionUtils.isEmpty(menuList)) {
            Set<String> permissionSet = new HashSet<>();
            for (Menu menu : menuList) {
                String permission = null;
                if (!StringUtils.isEmpty(permission = menu.getPerms())) {
                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
                }
            }
            info.setStringPermissions(permissionSet);
        }
        return info;
    }
}
