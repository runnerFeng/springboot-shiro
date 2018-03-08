package com.example.controller;

import com.example.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinx
 * @date 2018/3/7 17:36
 * Desc:
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, String vcode, Boolean rememberMe) {
        System.out.println(username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        SecurityUtils.getSubject().login(token);
        return "loginSuccess";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return user.getUsername();
    }
}
