package com.poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.service.SessionService;
import com.poly.service_bean.UsersService;

@Controller
public class AccountController {
	
	@Autowired
	UsersService accountService;
	
	@Autowired
	SessionService ss;

	@RequestMapping("/account/login/form")
	public String getLogin(Model m) {
		return "account/login";
	}
	
	@RequestMapping("/account/login/success")
	public String success(Model model) {
//		model.addAttribute("message", "Đăng nhập thành công!");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         List<String> authList = new ArrayList<>();
         // Check if the user is authenticated
         if (authentication != null && authentication.isAuthenticated()) {
            List<String> roleNames = accountService.getRolesByUsername(authentication.getName());

            for (String roleName : roleNames) {
               authList.add("ROLE_" + roleName);
            }
         }

		if(authList.contains("ROLE_ADMIN")){
			
			return "redirect:/admin/index";
		} 
		else{
			ss.setAttribute("auth", authentication);
			return"redirect:/home/index";
		}
		
	}
	
	@RequestMapping("/account/login/error")
	public String loginError(Model model) {
//		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "account/login";
	}
	

//	@RequestMapping("/account/unauthoried")
//	public String unauthoried(Model model) {
//		model.addAttribute("message", "Không có quyền truy xuất!");
//		return "account/login";
//	}

	@RequestMapping("/account/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
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
	
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
}
