package com.quanlychitieu.entity;

import com.quanlychitieu.utils.Utils;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "password_reset_token")
public class PasswordResetToken {
    private static final int EXPIRATION_HOUR = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;

    private String token;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    public PasswordResetToken() {

    }

    public PasswordResetToken(String token, User user, Date expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = Utils.getAnHourLateThanNowDate(EXPIRATION_HOUR);
    }

    public Long getTokenId() {
        return tokenId;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
