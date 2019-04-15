package com.quanlychitieu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WalletController {

    @RequestMapping("/viewWallet")
    public String viewWallet() {
        return "page/viewWallet";
    }
}
