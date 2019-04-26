package com.quanlychitieu.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.quanlychitieu.entity.Currency;
import com.quanlychitieu.entity.TransactionType;
import com.quanlychitieu.service.CategoryService;
import com.quanlychitieu.service.TransactionService;
import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WalletController {
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private WalletService walletService;

	@Autowired
    private TransactionService transactionService;

	@Autowired
    private CategoryService categoryService;

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
            Iterator<User> it = wallet.getListUser().iterator();
            while (it.hasNext()) {
                if (it.next().getUserId() == user.getUserId()) {
                    it.remove();
                    break;
                }
            }

            model.addAttribute("sum", sum);           
			model.addAttribute("inflow",inflow);
            model.addAttribute("outflow",outflow);
            model.addAttribute("user", user);
            model.addAttribute("userList", wallet.getListUser());
            model.addAttribute("wallet", wallet);
            model.addAttribute("transactionsByDateMap", getTransactionListByDateMap(listTransaction));
        }
        return page;
    }


    @RequestMapping(value = "/viewWallet{walletId}/getListTransactionByMonth", method = RequestMethod.GET)
    public @ResponseBody String getListTransactionByMonth(HttpServletRequest request, @PathVariable(value = "walletId") int walletId) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        String yearMonth = request.getParameter("yearMonth");
        YearMonth month = null;
        try {
             month = YearMonth.parse(yearMonth);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        List<Transaction> transactionListInMonth = transactionService.getListTransactionByMonth(walletId, month);

        try {
            ajaxResponse = mapper.writeValueAsString(transactionListInMonth);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return ajaxResponse;
    }

    private Map<Date, List<Transaction>> getTransactionListByDateMap(List<Transaction> transactionList) {
        Map<Date, List<Transaction>> map = new TreeMap<>((o1, o2) -> -o1.compareTo(o2));
        try {
            for (Transaction transaction: transactionList) {
                List<Transaction> transactions = map.get(transaction.getDate());
                if (transactions == null) {
                    transactions = new ArrayList<>();
                    transactions.add(transaction);
                    map.put(transaction.getDate(), transactions);
                }
                else {
                    transactions.add(transaction);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }

    @RequestMapping(value = "/deleteTransaction", method = RequestMethod.GET)
    public @ResponseBody String deleteTransaction(Principal principal, HttpServletRequest request) {
        String email = principal.getName();
        int transactionId = Integer.parseInt(request.getParameter("transactionId"));
        int walletId = Integer.parseInt(request.getParameter("walletId"));

        return transactionService.deleteTransaction(transactionId, walletId, email);
    }

    @RequestMapping(value = "/addTransaction", method = RequestMethod.GET)
    public @ResponseBody String addTransaction(Principal principal, HttpServletRequest request) {
        String email = principal.getName();
        int categoryId = Integer.parseInt(request.getParameter("category-id"));
        TransactionType type = TransactionType.valueOf(request.getParameter("transaction-type"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String note = request.getParameter("note");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date-transaction"));
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }
        int walletId = Integer.parseInt(request.getParameter("wallet-id"));

        return transactionService.addTransaction(categoryId, type, amount, note, date, walletId, email);
    }

    @RequestMapping(value = "/addSharedUser", method = RequestMethod.GET)
    public @ResponseBody String addSharedUser(HttpServletRequest request, Principal principal) {
        int walletId = Integer.parseInt(request.getParameter("wallet-id"));
        String sharedUserEmail = request.getParameter("shared-user");
        String ownerEmail = principal.getName();

        return walletService.addSharedUserIntoWallet(walletId, ownerEmail, sharedUserEmail);
    }

    @RequestMapping(value = "/deleteSharedUser", method = RequestMethod.GET)
    public @ResponseBody String deleteSharedUser(HttpServletRequest request, Principal principal) {
        String ownerEmail = principal.getName();
        int sharedUserId = Integer.parseInt(request.getParameter("shared-user-id"));
        int walletId = Integer.parseInt(request.getParameter("wallet-id"));

        return walletService.deleteSharedUserFromWallet(walletId, ownerEmail, sharedUserId);
    }

    @RequestMapping(value = "/deleteWallet", method = RequestMethod.GET)
    public @ResponseBody String deleteWallet(HttpServletRequest request, Principal principal, HttpSession session) {
        int walletId = Integer.parseInt(request.getParameter("wallet-id"));
        String email = principal.getName();
        String message = walletService.deleteWallet(walletId, email);
        User user = userService.getUser(email);
        session.setAttribute("walletList", user.getListWallet());
        return message;
    }

    @RequestMapping(value = "/addWallet", method = RequestMethod.GET)
    public @ResponseBody String addWallet(HttpServletRequest request, Principal principal, HttpSession session) {
        String walletName = request.getParameter("wallet-name");
        Currency currency = Currency.valueOf(request.getParameter("currency"));
        String email = principal.getName();
        User user = userService.getUser(email);
        String message = walletService.addWallet(walletName, currency, user);
        user = userService.getUser(email);
        session.setAttribute("walletList", user.getListWallet());
        return message;
    }
    
}
