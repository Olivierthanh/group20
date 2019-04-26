package com.quanlychitieu.service;

import com.quanlychitieu.dao.PasswordResetTokenDao;
import com.quanlychitieu.dao.UserDao;
import com.quanlychitieu.entity.Gender;
import com.quanlychitieu.entity.PasswordResetToken;
import com.quanlychitieu.entity.User;
import com.quanlychitieu.entity.Wallet;
import com.quanlychitieu.exception.EmptyInputException;
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
import java.util.UUID;

@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordResetTokenDao passwordResetTokenDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User getUser(String email) {
        return userDao.getUserByEmail(email);
    }

    public String registerUser(HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        AjaxMessage message;
        String ajaxResponse = "";
        User user;
        try {
            user = getUserFromFormData(request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            boolean isSaved = userDao.saveUser(user);
            if (isSaved) {
                message = new AjaxMessage("success", "Register successfully", "Please return login page to login");
            }
            else {
                message = new AjaxMessage("error", "Register unsuccessfully", "This email " + user.getEmail() + " is already existed, please use another email");
            }
        }
        catch (ParseException parseException) {
            parseException.printStackTrace();
            message = new AjaxMessage("error", "Error", "Wrong date format (expected yyyy-MM-dd)");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            message = new AjaxMessage("error", "Error", "Some thing wrong happen");
        }

        try {
            ajaxResponse = mapper.writeValueAsString(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return ajaxResponse;
    }

    public String updatePassword(String oldPassword, String newPassword, String email){
        String ajaxResponse = "";
        AjaxMessage message;
        User user = userDao.getUserByEmail(email);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            if (userDao.updateUser(user)) {
                message = new AjaxMessage("success", "Updated password", "Your password is updated");
            }
            else {
                message = new AjaxMessage("error", "Failed to update password", "Your password is not updated, please try gain");
            }
        }
        else {
            message = new AjaxMessage("error", "Incorrect password", "Your old password is incorrect, please try gain");
        }

        try {
            ajaxResponse = new ObjectMapper().writeValueAsString(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return ajaxResponse;
    }

    public String updateProfile(HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        AjaxMessage message;
        String ajaxResponse = "";
        User newProfile;
        try {
            newProfile = getUserFromFormData(request);
            System.out.println(newProfile.getEmail());
            User oldProfile = userDao.getUserByEmail(newProfile.getEmail());
            System.out.println(oldProfile);
            newProfile.setListWallet(oldProfile.getListWallet());
            newProfile.setUserId(oldProfile.getUserId());

            if (passwordEncoder.matches(newProfile.getPassword(), oldProfile.getPassword())) {
                newProfile.setPassword(oldProfile.getPassword());
                boolean isUpdated = userDao.updateUser(newProfile);
                if (isUpdated) {
                    message = new AjaxMessage("success", "Update successfully", "Your profile is updated");
                }
                else {
                    message = new AjaxMessage("error", "Update unsuccessfully", "Your profile is not updated! Please try to update profile again");
                }
            }
            else {
                message = new AjaxMessage("error", "Incorrect Password", "Please enter the correct password");
            }

        }
        catch (ParseException parseException) {
            parseException.printStackTrace();
            message = new AjaxMessage("error", "Error", "Wrong date format (expected yyyy-MM-dd)");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            message = new AjaxMessage("error", "Error", "Some thing wrong happen");
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
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        Set<Wallet> listWallet = new HashSet<Wallet>();
        Date dateOfBirth = Utils.getDate(request.getParameter("date-of-birth"));

        return new User(email, password, name, address, listWallet, dateOfBirth, gender);
    }

    private PasswordResetToken createPasswordResetTokenForUser(String email) {
        User user = userDao.getUserByEmail(email);
        String token = UUID.randomUUID().toString();

        PasswordResetToken passwordResetToken = passwordResetTokenDao.getPasswordResetTokenByToken(token);
        if (passwordResetToken != null) {
            passwordResetToken.setToken(token);
        }
        else {
            passwordResetToken = new PasswordResetToken(token, user);
        }
        boolean isTokenCreated = passwordResetTokenDao.savePasswordResetToken(passwordResetToken);
        return isTokenCreated ? passwordResetToken : null;
    }

    public String getResetPasswordUrl(String email, HttpServletRequest request) {
        PasswordResetToken passwordResetToken = createPasswordResetTokenForUser(email);
        if (passwordResetToken == null) {
            return null;
        }
        String contextPath = request.getContextPath();
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        String serverAddress = requestURL.substring(0, requestURL.length() - requestURI.length());
        int userId = passwordResetToken.getUser().getUserId();
        String token = passwordResetToken.getToken();
        return serverAddress + contextPath + "/changePassword?userId=" + userId + "&token=" + token;
    }

    private PasswordResetToken getPasswordResetToken(HttpServletRequest request) throws EmptyInputException {
        PasswordResetToken passwordResetToken = null;
        String userId = request.getParameter("userId");
        String token = request.getParameter("token");

        if (userId != null && token != null) {
            passwordResetToken = passwordResetTokenDao.getPasswordResetTokenByToken(token);
        }
        else {
            throw new EmptyInputException("Token is empty");
        }

        return passwordResetToken;
    }

    private boolean isValidPasswordResetToken(PasswordResetToken passwordResetToken, int userId) throws EmptyInputException {
        return passwordResetToken != null && passwordResetToken.getUser().getUserId() == userId && passwordResetToken.getExpiryDate().compareTo(new Date()) > 0;
    }

    public String processChangePassword(HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        String returnMessage = "";
        AjaxMessage message;
        try {
            PasswordResetToken passwordResetToken = getPasswordResetToken(request);
            int userId = Integer.parseInt(request.getParameter("userId"));
            if (isValidPasswordResetToken(passwordResetToken, userId)) {
                String newPassword = request.getParameter("password");
                User user = passwordResetToken.getUser();
                user.setPassword(passwordEncoder.encode(newPassword));
                userDao.updateUser(user);
                passwordResetTokenDao.deletePasswordResetToken(passwordResetToken);
                message = new AjaxMessage("success", "Change password successfully", "Your password is changed! Please return login page to login");
            }
            else {
                message = new AjaxMessage("error", "Token is invalid or expired", "Your password was not changed");
            }
        }
        catch (EmptyInputException ex) {
            message = new AjaxMessage("error", "Some thing wrong happen", ex.getMessage());
            ex.printStackTrace();
        }

        try {
            returnMessage = mapper.writeValueAsString(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnMessage;
    }

}
