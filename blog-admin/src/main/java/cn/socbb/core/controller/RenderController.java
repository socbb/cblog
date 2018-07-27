package cn.socbb.core.controller;

import cn.socbb.core.bean.system.Role;
import cn.socbb.core.bean.system.User;
import cn.socbb.core.service.system.MenuService;
import cn.socbb.core.service.system.RoleService;
import cn.socbb.core.service.system.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 页面跳转
 * Created by cbb on 2018/7/17.
 */
@Controller
public class RenderController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @RequiresPermissions("menus")
    @GetMapping("menus")
    public String menu(){
        return "system/menu/list";
    }

    @RequiresPermissions(value = {"menu:add", "menu:edit"}, logical = Logical.OR)
    @GetMapping("menu/form")
    public String menuform(Long id, Model model){
        model.addAttribute("bean", menuService.findById(id));
        return "system/menu/form";
    }

    @RequiresPermissions("roles")
    @GetMapping("roles")
    public String role(){
        return "system/role/list";
    }

    @RequiresPermissions(value = {"role:add", "role:edit"}, logical = Logical.OR)
    @GetMapping("role/form")
    public String roleEdit(Long id, Model model){
        model.addAttribute("bean", roleService.findById(id));
        return "system/role/form";
    }

    @RequiresPermissions("users")
    @GetMapping("users")
    public String user(){
        return "system/user/list";
    }

    @RequiresPermissions(value = {"user:add", "user:edit"}, logical = Logical.OR)
    @GetMapping("user/form")
    public String userEdit(Long id, Model model){
        model.addAttribute("bean", userService.findById(id));
        List<Role> roles = roleService.findRolesByUserId(id);
        model.addAttribute("roleIds", roles.stream().map(Role::getId).collect(Collectors.toList()));
        return "system/user/form";
    }

    @RequiresPermissions("dicts")
    @GetMapping("dicts")
    public String dict(){
        return "system/dict/list";
    }

    @RequiresPermissions(value = {"dict:add", "dict:edit"}, logical = Logical.OR)
    @GetMapping("dict/form")
    public String dictEdit(Long id, Model model){
        // TODO 字典service
//        model.addAttribute("bean", userService.findById(id));
        return "system/dict/form";
    }

    @RequiresPermissions("configs")
    @GetMapping("config")
    public String config(){
        return "system/config/list";
    }
}
