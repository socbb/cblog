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

    @RequiresPermissions("system:menu:list")
    @GetMapping("/system/menu/list")
    public String menu(){
        return "system/menu/list";
    }

    @RequiresPermissions("system:role:list")
    @GetMapping("/system/role/list")
    public String role(){
        return "system/role/list";
    }

    @RequiresPermissions("system:user:list")
    @GetMapping("/system/user/list")
    public String user(){
        return "system/user/list";
    }

    @RequiresPermissions("system:dict:list")
    @GetMapping("/system/dict/list")
    public String dict(){
        return "system/dict/list";
    }

    @RequiresPermissions("system:config:list")
    @GetMapping("/system/config/list")
    public String config(){
        return "system/config/list";
    }
}
