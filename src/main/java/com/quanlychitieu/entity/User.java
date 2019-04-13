package com.quanlychitieu.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;


@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String email;
    private String password;
    private String name;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_wallet", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "walletId")})
    private Set<Wallet> listWallet;

    public Set<Wallet> getListWallet() {
        return listWallet;
    }

    public void setListWallet(Set<Wallet> listWallet) {
        this.listWallet = listWallet;
    }

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User() {

    }

    public User(int userId, String email, String password, String name, String address, Set<Wallet> listWallet,
                Date dateOfBirth, Gender gender) {
        super();
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.listWallet = listWallet;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public User(String email, String password, String name, String address, Set<Wallet> listWallet,
                Date dateOfBirth, Gender gender) {
        super();
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.listWallet = listWallet;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


}
