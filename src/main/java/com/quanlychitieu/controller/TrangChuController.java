package com.quanlychitieu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrangChuController {

	@RequestMapping("/")
	public String Default() {
		return "trangchu";
	}
}
