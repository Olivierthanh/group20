package com.quanlychitieu.controller;

import com.quanlychitieu.entity.*;
import com.quanlychitieu.service.CategoryService;
import com.quanlychitieu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

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
    public String home(Principal principal, HttpSession session, Model model) {
        Set<Wallet> walletList;
        if (!isUsernameSet(session)) {
            String email = principal.getName();
            User user = userService.getUser(email);

            session.setAttribute("username", user.getName());
            session.setAttribute("walletList", user.getListWallet());
        }
        walletList = (Set<Wallet>) session.getAttribute("walletList");

        model.addAttribute("noWallet", walletList.size());
        model.addAttribute("noTransaction", getNoTransaction(walletList));

        int noSharedWallet = 0;
        int noPersonalWallet = 0;
        for(Wallet wallet: walletList) {
            if (wallet.getWalletType().equals(WalletType.shared)) {
                noSharedWallet++;
            }
            else {
                noPersonalWallet++;
            }
        }
        model.addAttribute("noSharedWallet", noSharedWallet);
        model.addAttribute("noPersonalWallet", noPersonalWallet);
        if (!isCategoryListSet(session)) {
            List<Category> categoryList = categoryService.getAllCategoryList();
            List<Category> incomeCategoryList = categoryService.getCategoryListByType(categoryList, TransactionType.income);
            List<Category> expenseCategoryList = categoryService.getCategoryListByType(categoryList, TransactionType.expense);
            session.setAttribute("incomeCategoryList", incomeCategoryList);
            session.setAttribute("expenseCategoryList", expenseCategoryList);
        }

        if (!isCurrencySet(session)) {
            session.setAttribute("currencyList", Currency.values());
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

    private boolean isCategoryListSet(HttpSession session) {
        return session.getAttribute("incomeCategoryList") != null && session.getAttribute("expenseCategoryList") != null;
    }

    private boolean isCurrencySet(HttpSession session) {
        return session.getAttribute("currencyList") != null;
    }

    private int getNoTransaction(Set<Wallet> walletList) {
        int noTransaction = 0;
        for (Wallet wallet: walletList) {
            noTransaction += wallet.getListTransaction().size();
        }
        return noTransaction;
    }

}
