package com.quanlychitieu.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quanlychitieu.entity.Transaction;
import com.quanlychitieu.entity.User;
import com.quanlychitieu.entity.Wallet;
import com.quanlychitieu.service.UserService;
import com.quanlychitieu.service.WalletService;

@Controller
public class WalletController {
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private WalletService walletService;

    @RequestMapping("/viewWallet{walletId}")
    public String viewWallet(Principal principal, HttpSession session , Model model, @PathVariable(value = "walletId") int walletId	) {
    	String page;
        if (session.isNew()) {
            page = "redirect:/home";
        }
        else {
            page = "page/viewWallet";
            String email = principal.getName();
            User user = userService.getUser(email);
            Wallet wallet = walletService.getWallet(walletId);
            int inflow = walletService.getInflow(walletId);
            int outflow = walletService.getOutflow(walletId);
            int sum = inflow + outflow;
            List<Transaction> listTransaction = walletService.getTransactionByWalletId(walletId);
            model.addAttribute("sum", sum);           
			model.addAttribute("inflow",inflow);
            model.addAttribute("outflow",outflow);
            model.addAttribute("user", user);
            model.addAttribute("wallet", wallet);
            model.addAttribute("listTransaction", listTransaction);
        }
        return page;
    }
    
    
}
