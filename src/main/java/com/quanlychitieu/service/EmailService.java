package com.quanlychitieu.service;

import com.quanlychitieu.utils.AjaxMessage;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailService {
    private static final String SUBJECT = "Wallet - Khôi phục mật khẩu tài khoản";
    private static final String MESSAGE = "Hãy truy cập đường link này để khôi phục lại tài khoản: \n";

    @Autowired
    private JavaMailSender mailSender;

    private SimpleMailMessage createPasswordResetTokenEmail(String url, String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(SUBJECT);
        mailMessage.setTo(email);
        mailMessage.setText(MESSAGE + url);
        mailMessage.setSentDate(new Date());
        return mailMessage;
    }

    public String sendPasswordResetTokenMail(String url, String email) {
        String ajaxResponse;
        ObjectMapper mapper = new ObjectMapper();
        AjaxMessage message;
        SimpleMailMessage mailMessage = createPasswordResetTokenEmail(url, email);
        try {
            mailSender.send(mailMessage);
            message = new AjaxMessage("success", "Gửi email thành công", "Hãy check hộp thư của bạn để khôi phục mật khẩu!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            message = new AjaxMessage("error", "Không thể gửi email", "Hãy thử lại!");
        }

        try {
            ajaxResponse = mapper.writeValueAsString(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            ajaxResponse = null;
        }
        return ajaxResponse;
    }
}
