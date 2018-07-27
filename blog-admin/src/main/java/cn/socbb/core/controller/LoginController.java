package cn.socbb.core.controller;

import cn.socbb.common.support.Response;
import cn.socbb.common.utils.PasswordUtils;
import cn.socbb.common.utils.SessionUtil;
import cn.socbb.core.bean.system.User;
import cn.socbb.core.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 登陆注册相关
 * Created by cbb on 2018/7/21.
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String login(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            return "redirect:/index";
        }
//        model.addAttribute("enableKaptcha", config.getEnableKaptcha());
        return "login";
    }

    @GetMapping("register")
    public String register(Model model) {
        return "register";
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Response submitLogin(String username, String password, boolean rememberMe, String kaptcha) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到xxRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
            return Response.success();
        } catch (Exception e) {
            log.error("登录失败，用户名[{}]", username, e);
            token.clear();
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改密码
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/updatePwd")
    public Response updatePwd(User user, BindingResult bindingResult) {
        // TODO 待完善
        if (bindingResult.hasErrors()) {
            return Response.error(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = userService.updatePwd(user);
        SessionUtil.removeAllSession();
        return result ? Response.success() : Response.error("密码修改失败");
    }

    @ResponseBody
    @PostMapping("/register")
    public Response register(User user) {
        User u = userService.findByUsername(user.getUsername());
        if (u != null) {
            return Response.error("该用户名[" + user.getUsername() + "]已存在！请更改用户名");
        }
        try {
            user.setPassword(PasswordUtils.encrypt(user.getPassword(), user.getUsername()));
            userService.save(user);
            return Response.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("error");
        }
    }

    /**
     * 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
     *
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        // http://www.oschina.net/question/99751_91561
        // 此处有坑： 退出登录，其实不用实现任何东西，只需要保留这个接口即可，也不可能通过下方的代码进行退出
        // SecurityUtils.getSubject().logout();
        // 因为退出操作是由Shiro控制的
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "login";
    }
}
