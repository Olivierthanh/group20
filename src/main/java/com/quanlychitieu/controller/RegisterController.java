package com.quanlychitieu.controller;

import com.quanlychitieu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register() {
        return "page/register";
    }

    @RequestMapping(value = "/register_user", method = RequestMethod.GET)
    public @ResponseBody String registerUser(HttpServletRequest request) {
        System.out.println(request.getParameter("email"));
        String message = userService.registerUser(request);
        return message;
    }
}
