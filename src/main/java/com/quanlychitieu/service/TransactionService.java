package com.quanlychitieu.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanlychitieu.dao.TransactionDao;
import com.quanlychitieu.entity.Gender;
import com.quanlychitieu.entity.Transaction;
import com.quanlychitieu.entity.User;
import com.quanlychitieu.entity.Wallet;
import com.quanlychitieu.utils.AjaxMessage;
import com.quanlychitieu.utils.Utils;

@Service
public class TransactionService {

	@Autowired
	private TransactionDao transactionDao;
	
	
//	public String addTransaction(HttpServletRequest request) {
//        ObjectMapper mapper = new ObjectMapper();
//        AjaxMessage message;
//        String ajaxResponse = "";
//        User user;
//        try {
//            Transaction = getTransactionFromFormData(request);
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            boolean isSaved = userDao.saveUser(user);
//            if (isSaved) {
//                message = new AjaxMessage("success", "Register successfully", "Please return login page to login");
//            }
//            else {
//                message = new AjaxMessage("error", "Register unsuccessfully", "This email " + user.getEmail() + " is already existed, please use another email");
//            }
//        }
//        catch (ParseException parseException) {
//            parseException.printStackTrace();
//            message = new AjaxMessage("error", "Error", "Wrong date format (expected yyyy-MM-dd)");
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//            message = new AjaxMessage("error", "Error", "Some thing wrong happen");
//        }
//
//        try {
//            ajaxResponse = mapper.writeValueAsString(message);
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return ajaxResponse;
//    }
//
//
//	private Transaction getTransactionFromFormData(HttpServletRequest request) {
//        String category = request.getParameter("category");
//        String amount = request.getParameter("amount");
//        String note = request.getParameter("note");
//        String address = request.getParameter("address");
//        Gender gender = Gender.valueOf(request.getParameter("gender"));
//        Set<Wallet> listWallet = new HashSet<Wallet>();
//        Date date = Utils.getDate(request.getParameter("date-transaction"));
//
//        return 
//	}
	
	
}
