package com.poly.service_bean;

import java.util.List;
import java.util.Optional;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.poly.bean.Users;


public interface UsersService {

	public List<Users> findAll();

	public Users findById(Integer id);

	public Users create(Users u);

	public Users update(Users u);

	public void delete(Integer id);
	
	public Users findByObject(String email);
	
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2);
}
