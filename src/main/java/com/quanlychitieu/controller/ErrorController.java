package com.quanlychitieu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    @RequestMapping("/pageNotFound")
    public String handlePageNotFound() {
        return "page/error/404";
    }

    @RequestMapping("/badRequest")
    public String handleBadRequest() {
        return "page/error/400";
    }

    @RequestMapping("/internalServerError")
    public String handleInternalServerError(){
        return "page/error/500";
    }

    @RequestMapping("/forbiddenError")
    public String handleForbiddenError() {
        return "page/error/403";
    }

    @RequestMapping("/serverUnavailable")
    public String handleServerUnavailable() {
        return "page/error/503";
    }
}
