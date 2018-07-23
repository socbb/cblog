package cn.socbb.core.controller;

import cn.socbb.common.support.PageResult;
import cn.socbb.common.utils.ResultUtils;
import cn.socbb.core.bean.system.User;
import cn.socbb.core.service.system.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
