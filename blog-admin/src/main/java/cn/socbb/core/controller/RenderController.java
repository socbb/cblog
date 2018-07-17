package cn.socbb.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面跳转
 * Created by cbb on 2018/7/17.
 */
@Controller
public class RenderController {

    @GetMapping(value = {"/login", "/"})
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

}
