package com.quanlychitieu.controller;

import com.quanlychitieu.entity.User;
import com.quanlychitieu.service.EmailService;
import com.quanlychitieu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

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

    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public @ResponseBody String resetPassword(HttpServletRequest request) {
        String email = request.getParameter("email");
        String resetPasswordUrl = userService.getResetPasswordUrl(email, request);
        return emailService.sendPasswordResetTokenMail(resetPasswordUrl, email);
    }

    @RequestMapping("/changePassword")
    public String changePassword(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String token = request.getParameter("token");
        model.addAttribute("userId", userId);
        model.addAttribute("token", token);
        return "page/changePassword";
    }

    @RequestMapping(value = "/changePasswordProcess", method = RequestMethod.GET)
    public @ResponseBody String changePasswordProcess(HttpServletRequest request) {
        return userService.processChangePassword(request);
    }

    @RequestMapping("/updatePassword")
    public String updatePasswordView(Model model, Principal principal) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "page/updatePassword";
    }

    @RequestMapping(value = "/updatePasswordProcess", method = RequestMethod.GET)
    public @ResponseBody String updatePasswordProcess(HttpServletRequest request, Principal principal) {
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String email = principal.getName();
        return userService.updatePassword(oldPassword, newPassword, email);
    }
}
