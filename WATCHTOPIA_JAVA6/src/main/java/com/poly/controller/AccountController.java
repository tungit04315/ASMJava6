package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.UserRole;
import com.poly.bean.Users;
import com.poly.service_bean.RoleService;
import com.poly.service_bean.UserRoleService;
import com.poly.service_bean.UsersService;
import com.poly.service_impl.UsersServiceImpl;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;


@Controller
public class AccountController {
	@Autowired
	UsersService userService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/account/login")
	public String getLogin(Model m) {
		return "account/login";
	}
	
	@GetMapping("/account/register/form")
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
	
	//POST
	@PostMapping("/account/registers")
	public String Register(Model m, Users u, @Param("username") String username) {
		//System.out.println("xuất mẫu:"+userService.findById(username));
		Users ufind = userService.findById(username);
		if(ufind != null) {
			m.addAttribute("errorUsername","true");
		}else {
			userService.create(u);
			
			UserRole uRole = new UserRole();
			uRole.setUserss(u);
			uRole.setRole(roleService.findbyId("USER"));
			userRoleService.create(uRole);
			
			m.addAttribute("successRegister", "true");
		}
		return "account/register";
	}
	
	@PostMapping("/account/changeprofile")
	public String ChangeProfile(Model m, Users u,@Param("username") String username) {
		userService.update(u);
		return "/home/profile";
	}
}
