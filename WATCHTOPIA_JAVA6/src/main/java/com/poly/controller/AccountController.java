package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

	@RequestMapping("/account/login")
	public String getLogin(Model m) {
		return "account/login";
	}
	
	@RequestMapping("/account/register")
	public String getRegister(Model m) {
		return "account/register";
	}
	
	@RequestMapping("/account/forget")
	public String getForgetPassword(Model m) {
		return "account/forgetPassword";
	}
	
	@RequestMapping("/account/change")
	public String getChange(Model m) {
		return "account/changePassword";
	}
	
	@RequestMapping("/account/OTP")
	public String getOTP(Model m) {
		return "account/otp";
	}
}
