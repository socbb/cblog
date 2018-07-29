package cn.socbb.core.controller;

import cn.socbb.common.enums.UserTypeEnum;
import cn.socbb.common.support.PageResult;
import cn.socbb.common.support.Response;
import cn.socbb.common.utils.PasswordUtils;
import cn.socbb.common.utils.ResultUtils;
import cn.socbb.common.utils.StringUtils;
import cn.socbb.core.bean.system.User;
import cn.socbb.core.service.system.UserService;
import com.alibaba.fastjson.JSON;
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
@RequestMapping("/user")
public class RestUserController {

    @Autowired
    private UserService userService;

    @RequiresPermissions("users")
    @PostMapping("/list")
    public PageResult list(User user){
        PageHelper.startPage(1, 10);
        List<User> list = userService.findByUser(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return ResultUtils.tablePage(pageInfo);
    }

    @GetMapping("/get/{id}")
    public Response get(@PathVariable Long id) {
        return Response.success(userService.findById(id));
    }

    @RequiresPermissions(value = {"user:add", "user:edit"}, logical = Logical.OR)
    @PostMapping(value = "/save")
    public Response save(User user) {
        if (user.getId() == null) {
            User u = userService.findByUsername(user.getUsername());
            if (u != null) {
                return Response.error("该用户名["+user.getUsername()+"]已存在！");
            }
            try {
                user.setPassword(PasswordUtils.encrypt(user.getPassword(), user.getUsername()));
                user.setType(UserTypeEnum.ADMIN.toString());
                userService.save(user);
                return Response.success("成功");
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error("error");
            }
        } else {
            try {
                userService.update(user);
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error("用户修改失败！");
            }
            return Response.success();
        }
    }

    @RequiresPermissions("user:delete")
    @PostMapping(value = "/delete")
    public Response delete(@RequestParam("ids[]") Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return Response.error("请至少选择一条记录");
        }
        userService.deleteWhitRoleById(ids);
        return Response.success("成功删除 [" + ids.length + "] 条记录");
    }

    /**
     * 分配的角色
     */
    @RequiresPermissions("user:allotRole")
    @PostMapping("/allotRole/{userId}")
    public Response allotRoleSave(@PathVariable Long userId, String roleId){
        List<Long> roleIds = JSON.parseArray(roleId, Long.TYPE);
        userService.allotRole(userId, roleIds);
        return Response.success();
    }
}
