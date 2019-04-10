package com.quanlychitieu.service;

import com.quanlychitieu.dao.UserDao;
import com.quanlychitieu.entity.Gender;
import com.quanlychitieu.entity.User;
import com.quanlychitieu.entity.Wallet;
import com.quanlychitieu.utils.AjaxMessage;
import com.quanlychitieu.utils.Utils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User getUser(String email) {
        return userDao.getUserByEmail(email);
    }

    public String registerUser(HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        AjaxMessage message = new AjaxMessage();
        String ajaxResponse = "";
        User user;
        try {
            user = getUserFromFormData(request);
            boolean isSaved = userDao.saveUser(user);
            if (isSaved) {
                message.setTitle("Register successfully");
                message.setType("success");
                message.setMessage("Please return login page to login");
            }
            else {
                message.setTitle("Register unsuccessfully");
                message.setType("error");
                message.setMessage("This email " + user.getEmail() + " is already existed, please use another email");
            }
        }
        catch (ParseException parseException) {
            parseException.printStackTrace();
            message.setTitle("Error");
            message.setType("error");
            message.setMessage("Wrong date format (expected yyyy-MM-dd)");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            message.setTitle("Error");
            message.setType("error");
            message.setMessage("Some thing wrong happen");
        }

        try {
            ajaxResponse = mapper.writeValueAsString(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return ajaxResponse;
    }

    private User getUserFromFormData(HttpServletRequest request) throws ParseException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = passwordEncoder.encode(request.getParameter("password"));
        String address = request.getParameter("address");
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        Set<Wallet> listWallet = new HashSet<Wallet>();
        Date dateOfBirth = Utils.getDate(request.getParameter("date-of-birth"));

        return new User(email, password, name, address, listWallet, dateOfBirth, gender);
    }
}
