package cn.socbb.core.controller;

import cn.socbb.common.shiro.ShiroService;
import cn.socbb.common.support.PageResult;
import cn.socbb.common.support.Response;
import cn.socbb.common.utils.ResultUtils;
import cn.socbb.core.bean.system.Role;
import cn.socbb.core.service.system.MenuService;
import cn.socbb.core.service.system.RoleService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cbb on 2018/7/23.
 */
@RestController
@RequestMapping("/role")
public class RestRoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ShiroService shiroService;

    @RequiresPermissions("roles")
    @PostMapping("/list")
    public PageResult list(Role role){
        List<Role> list = roleService.findByRole(role);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return ResultUtils.tablePage(pageInfo);
    }

    /**
     * 获取可分配的角色
     */
    @RequiresPermissions("user:allotRole")
    @GetMapping("/allotRole/list/{userId}")
    public Response allotRoleList(@PathVariable Long userId){
        return Response.success(roleService.findRolesWithSelected(userId));
    }

    @GetMapping("/get/{id}")
    public Response get(@PathVariable Long id) {
        return Response.success(roleService.findById(id));
    }

    @RequiresPermissions(value = {"role:add", "role:edit"}, logical = Logical.OR)
    @PostMapping(value = "/save")
    public Response save(Role role) {
        if (role.getId() == null) {
            try {
                roleService.save(role);
                return Response.success();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error("保存失败！");
            }
        } else {
            try {
                roleService.update(role);
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error("保存失败！");
            }
            return Response.success();
        }
    }

    @RequiresPermissions("role:delete")
    @PostMapping(value = "/delete")
    public Response delete(@RequestParam("ids[]") Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return Response.error("请至少选择一条记录");
        }
        roleService.delete(ids);
        return Response.success("成功删除 [" + ids.length + "] 条记录");
    }

    @RequiresPermissions("role:allot")
    @PostMapping("/allot/get/{roleId}")
    public Response allotGet(@PathVariable Long roleId) {
        return Response.success(menuService.findWithSelected(roleId));
    }

    @RequiresPermissions("role:allot")
    @PostMapping("/allot/save")
    public Response saveRoleResources(Long roleId, String menuIds) {
        if (StringUtils.isEmpty(roleId)) {
            return Response.error("error");
        }
        try {
            List<Long> longs = JSON.parseArray(menuIds, Long.TYPE);
            menuService.addRoleMenus(roleId, longs.stream().toArray(Long[]::new));
            // 重新加载所有拥有roleId的用户的权限信息
            shiroService.reloadAuthorizingByRoleId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("保存失败");
        }
        return Response.success();
    }
}
