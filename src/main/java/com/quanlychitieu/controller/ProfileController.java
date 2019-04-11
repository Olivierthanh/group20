package com.quanlychitieu.controller;

import com.quanlychitieu.entity.User;
import com.quanlychitieu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @RequestMapping("/profile")
    public String viewProfile(Principal principal, Model model, HttpSession session) {
        String page;
        if (session.isNew()) {
            page = "redirect:/home";
        }
        else {
            page = "page/profile";
            String email = principal.getName();
            User user = userService.getUser(email);
            model.addAttribute("user", user);
        }
        return page;
    }

    @RequestMapping("/profile/update_profile")
    public @ResponseBody String updateProfile(HttpServletRequest request) {
        String message = userService.updateProfile(request);
        return message;
    }
}
