package cn.socbb.core.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面跳转
 * Created by cbb on 2018/7/17.
 */
@Controller
public class RenderController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @RequiresPermissions("menus")
    @GetMapping("menus")
    public String menu(){
        return "system/menu/list";
    }

    @RequiresPermissions("roles")
    @GetMapping("roles")
    public String role(){
        return "system/role/list";
    }

    @RequiresPermissions("users")
    @GetMapping("users")
    public String user(){
        return "system/user/list";
    }

    @RequiresPermissions("dicts")
    @GetMapping("dicts")
    public String dict(){
        return "system/dict/list";
    }

    @RequiresPermissions("configs")
    @GetMapping("config")
    public String config(){
        return "system/config/list";
    }
}
