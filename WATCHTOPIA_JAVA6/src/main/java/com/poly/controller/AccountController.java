package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service_impl.UsersServiceImpl;

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
