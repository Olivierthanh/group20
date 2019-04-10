package com.quanlychitieu.controller;

import com.quanlychitieu.entity.User;
import com.quanlychitieu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String root(Principal principal) {
        String page;
        if (!isLogin(principal)){
            System.out.println("Not login");
            page = "redirect:/login";
        }
        else {
            page = "redirect:/home";
        }
        return page;
    }

    @RequestMapping("/home")
    public String home(Principal principal, HttpSession session) {
        if (!isUsernameSet(session)) {
            String email = principal.getName();
            User user = userService.getUser(email);
            session.setAttribute("username", user.getName());
        }
        return "page/home";
    }

    private boolean isLogin(Principal principal) {
        boolean isLogin;
        if (principal != null) {
            String name = principal.getName();
            isLogin = name != null;
        }
        else {
            isLogin = false;
        }
        return isLogin;
    }

    private boolean isUsernameSet(HttpSession session) {
        return session.getAttribute("username") != null;
    }

}
