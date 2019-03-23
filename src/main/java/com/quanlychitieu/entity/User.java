package com.quanlychitieu.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

   enum Gender{ MALE, FEMALE }

@Entity(name="user")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String email;
	private String password;
	private String name;
	private String address;
	
	@Enumerated(EnumType.STRING)
    private Gender gender;
	
}
