package com.poly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.service_impl.UsersServiceImpl;

@Controller
public class SecurityController {
	@RequestMapping("/account/login/form")
	public String loginForm(Model model) {
		return "account/login";
	}

	@RequestMapping("/account/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "forward:/account/login/form";
	}

	@RequestMapping("/account/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "forward:/account/login/form";
	}

	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "forward:/account/login/form";
	}

	@RequestMapping("/account/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất!");
		return "forward:/account/login/form";
	}
	
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
	
	UsersServiceImpl usersServiceImpl;
	
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		usersServiceImpl.loginFromOAuth2(oauth2);
		return "forward:/oauth2/login/success";
	}
}
