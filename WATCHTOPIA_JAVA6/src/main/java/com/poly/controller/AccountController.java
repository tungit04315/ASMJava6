package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/account/register/form")
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
			uRole.setUsername(u);
			uRole.setRoleid(roleService.findbyId("USER"));
			userRoleService.create(uRole);
			
			m.addAttribute("successRegister", "true");
		}
		return "account/register";
	}
	
	@PostMapping("/account/changeprofile")
	public String ChangeProfile(Model m, Users u,@Param("username") String username) {
		userService.update(u);
		Users user =  (Users) ss.getAttribute("users");
		m.addAttribute("profile", userService.findById(user.getUsername()));
		return "home/profile";
	}
	
	@PostMapping("/account/changePassProfile")
	public String ChangePassProfile(Model m, Users u,@Param("passwords") String passwords) {
		Users user =  (Users) ss.getAttribute("users");
		
		String passwordsnew = param.getString("passwordsNew", "");
		String passwordsnew2 = param.getString("passwordsNew2", "");
		
		if(!passwords.equalsIgnoreCase(user.getPasswords())) {
			m.addAttribute("errorPass","Kiểm tra lại mật khẩu");
		}
		if(!passwordsnew.equalsIgnoreCase(passwordsnew2)) {
			m.addAttribute("errorPass","Kiểm tra lại mật khẩu");
		}
		else {
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
			userService.update(u);
			Users user2 =  (Users) ss.getAttribute("users");
			
			m.addAttribute("profile", userService.findById(user2.getUsername()));
			m.addAttribute("successPass",true);
		}
		return "home/profile";
	}
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
}
