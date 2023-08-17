package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.service.EmailSenderService;
import com.poly.service.ParamService;
import com.poly.service.SessionService;
import com.poly.service_bean.UsersService;

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

	@Autowired
	UsersService accountService;

	@Autowired
	EmailSenderService emailService;

	@Autowired
	SessionService ss;

	@Autowired
	ParamService param;

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

		if (authList.contains("ROLE_ADMIN")) {

			return "redirect:/admin/index";
		} else {
			ss.setAttribute("auth", ss.getAttribute("users"));
			return "redirect:/home/index";
		}
	}

	@RequestMapping("/account/login/error")
	public String loginError(Model model) {
		model.addAttribute("errorlogin", true);
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

	@RequestMapping("/account/register/form")
	public String getRegister(Model m) {
		return "account/register";
	}

	@RequestMapping("/account/forget")
	public String getForgetPassword(Model m) {
		return "account/forgetPassword";
	}

	// post forgetpass
	@PostMapping("/account/forgetPassword")
	public String forgetPass(Model m) {

		return null;
	}

	@RequestMapping("/account/change")
	public String getChange(Model m) {
		return "account/changePassword";
	}

	@RequestMapping("/account/OTP")
	public String getOTP(Model m) {
		return "account/otp";
	}

	// POST
	@PostMapping("/account/registers")
	public String Register(Model m, Users u, @Param("username") String username) {
		// System.out.println("xuất mẫu:"+userService.findById(username));
		Users ufind = userService.findById(username);
		if (ufind != null) {
			m.addAttribute("errorUsername", "true");
		} else {
			userService.create(u);

			UserRole uRole = new UserRole();
			uRole.setUsername(u);
			uRole.setRoleid(roleService.findbyId("USER"));
			userRoleService.create(uRole);

			m.addAttribute("successRegister", "true");
		}
		return "account/register";
	}

	@PostMapping("/account/changeprofile")
	public String ChangeProfile(Model m, Users u, @Param("username") String username) {
		userService.update(u);
		Users user = (Users) ss.getAttribute("users");
		m.addAttribute("u", userService.findById(user.getUsername()));
		return "home/profile";
	}

	@PostMapping("/account/changePassProfile")
	public String ChangePassProfile(Model m, 
			Users u, @Param("passwords") String passwords) {
		Users user = (Users) ss.getAttribute("users");

		String passwordsnew = param.getString("passwordsNew", "");
		String passwordsnew2 = param.getString("passwordsNew2", "");

		if (!passwords.equalsIgnoreCase(user.getPasswords())) {
			m.addAttribute("errorPass", true);
		}
		if (!passwordsnew.equalsIgnoreCase(passwordsnew2)) {
			m.addAttribute("errorPass", true);
		} else {
			if (passwords.equalsIgnoreCase(user.getPasswords())) {
				if (passwordsnew.equalsIgnoreCase(passwordsnew2)) {
					u.setUsername(user.getUsername());
					u.setFullname(user.getFullname());
					u.setPasswords(user.getPasswords());
					u.setEmail(user.getEmail());
					u.setPhone(user.getPhone());
					u.setActive(user.isActive());
					u.setCreatedate(user.getCreatedate());
					u.setFailed_login_attempts(user.getFailed_login_attempts());
					u.setBlocked(user.isBlocked());
					u.setLogs(user.getLogs());
					u.setUserRole(user.getUserRole());

					u.setPasswords(passwordsnew);
					user.setPasswords(passwordsnew);
					userService.update(u);

					m.addAttribute("u", u);
					m.addAttribute("successPass", true);
					return "home/profile";
				} 

			} 
		}
		m.addAttribute("errorPass", true);
		m.addAttribute("u", userService.findById(user.getUsername()));
		return "home/profile";
	}

//	@CrossOrigin("*")
//	@ResponseBody
//	@RequestMapping("/rest/security/authentication")
//	public Object getAuthentication(HttpSession session) {
//		return session.getAttribute("authentication");
//	}

	@GetMapping("/account/forgetpassword/form")
	public String getPassword() {
		return "account/forgetPassword";
	}

	@PostMapping("/account/forgetpassword/post")
	public String PostPassword(Model m, @Param("username") String username) {

		Users u = userService.findById(username);

		if (u != null) {

			int min = 1000;
			int max = 10000;

			int random = (int) (Math.floor(Math.random() * (max - min + 1)) + min);

			ss.setAttribute("usr", u);
			ss.setAttribute("otp", String.valueOf(random));
			emailService.sendSimpleEmail(u.getEmail(), "MÃ OTP", "MÃ OTP của bạn: " + random);

			return "account/otp";
		}

		m.addAttribute("errorPass", true);
		return "account/forgetPassword";
	}

	@PostMapping("/account/OTP")
	public String GetOTP(Model m, @Param("numberOne") String numberOne, @Param("numberTwo") String numberTwo,
			@Param("numberThree") String numberThree, @Param("numberFour") String numberFour) {

		String otp = numberOne + numberTwo + numberThree + numberFour;
		if (otp.equalsIgnoreCase(ss.getAttribute("otp"))) {
			return "account/changePassword";
		} else {
			int min = 1000;
			int max = 10000;

			int random = (int) (Math.floor(Math.random() * (max - min + 1)) + min);

			Users u = ss.getAttribute("usr");
			ss.setAttribute("otp", String.valueOf(random));
			emailService.sendSimpleEmail(u.getEmail(), "MÃ OTP", "MÃ OTP của bạn: " + random);

			m.addAttribute("errorPass", true);
			return "account/otp";
		}

	}

	@PostMapping("/account/changePassword")
	public String setChangePassword(Model m, Users u) {

		String passwordsnew = param.getString("passwords", "");
		String passwordsnew2 = param.getString("passwordsTwo", "");

		Users uFind = ss.getAttribute("usr");

		if (passwordsnew2.equalsIgnoreCase(passwordsnew)) {
			u.setUsername(uFind.getUsername());
			u.setFullname(uFind.getFullname());
			u.setPasswords(uFind.getPasswords());
			u.setEmail(uFind.getEmail());
			u.setPhone(uFind.getPhone());
			u.setActive(uFind.isActive());
			u.setCreatedate(uFind.getCreatedate());
			u.setFailed_login_attempts(uFind.getFailed_login_attempts());
			u.setBlocked(uFind.isBlocked());
			u.setLogs(uFind.getLogs());
			u.setUserRole(uFind.getUserRole());

			u.setPasswords(passwordsnew);
			userService.update(u);
			System.out.println("IF" + passwordsnew2);
			m.addAttribute("successPass", true);
			return "account/changePassword";
		}
		m.addAttribute("errorPass", true);
		System.out.println("ELSE" + passwordsnew);
		return "account/changePassword";
	}
}
