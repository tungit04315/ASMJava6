package com.poly.config;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.poly.bean.Users;
import com.poly.service_bean.UsersService;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	UsersService userService;

	@Autowired
	HttpSession session;

	// Cung cấp nguồn dữ liệu đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Users user = userService.findByObject(username);
				String password = pe.encode(user.getPasswords());
//				String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId())
//						.collect(Collectors.toList()).toArray(new String[0]);
				
//				boolean roles = user.isRoles();
						
				Map<String, Object> authentication = new HashMap<>();
				authentication.put("user", user);
				byte[] token = (username + ":" + user.getPasswords()).getBytes();
				authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
				session.setAttribute("authentication", authentication);
				return User.withUsername(username).password(password).build();
//				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + " not found!");
			}
		});
	}

	// Phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/order/**").authenticated()
		.antMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
		.antMatchers("/rest/authorities").hasRole("DIRE")
		.anyRequest().permitAll();
		
		http.formLogin().loginPage("/security/login/form")
		.loginProcessingUrl("/security/login")
		.defaultSuccessUrl("/security/login/success", false)
		.failureUrl("/security/login/error");
		
		http.rememberMe().tokenValiditySeconds(86400); // thoi gian luu token 24h
		
		http.exceptionHandling().accessDeniedPage("/security/unauthoried");
		
		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");
	}

	// Cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Cho phép truy xuất REST API từ domain khác
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
