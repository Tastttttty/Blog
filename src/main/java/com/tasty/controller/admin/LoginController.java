package com.tasty.controller.admin;

import com.tasty.model.User;
import com.tasty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/*
 *@author:zhouxin
 *@date:2021/12/14 16:06
 *@description:登录页面视图控制器
 */
@Controller
@RequestMapping("/admin") //总视图
public class LoginController {

    //自动装配service层实例
    @Autowired
    private UserService userService;


    //登录页面
    @GetMapping()
    public String loginPage(){
        return "admin/login";
    }

    //登录操作
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){
        //从前端输入的账户和秘密通过调用service对象同数据库进行比对，若比对成功则返回对象，若失败则返回null
        User user = userService.CheckUser(username, password);
        //账户密码存在时,将密码清0后，将登录信息传入session中
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        }
        //不存在时，重定向至登录页面
        else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

    //登出操作
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return  "redirect:/admin";
    }
}
/*
登录校验：将前端传递过来的用户名和密码给service进行检验核对，并放入session域中（session是全局的，登录后访问其他页面或者重开页面也是登录状态）
登录成功跳转后台管理页面，失败则跳转登录页面
登录成功后可以进行注销，注销后返回登录页面
*/