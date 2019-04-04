package com.quanlychitieu.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String Default(HttpSession session) {
        String page;
        if (!isLogin(session)){
            page = "redirect:/login";
        }
        else {
            page = "page/home";
        }
        return page;
    }

    private boolean isLogin(HttpSession session) {
        boolean isLogin;
        Object isLoginObject = session.getAttribute("is_login");
        if (isLoginObject != null) {
            isLogin = (Boolean) isLoginObject;
        }
        else {
            isLogin = false;
        }
        return isLogin;
    }
}
