package com.poly.service_bean;

import java.util.List;
import java.util.Optional;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.poly.bean.Users;


public interface UsersService {

	public List<Users> findAll();

	public Users findById(String id);

	public Users create(Users u);

	public Users update(Users u);

	public void delete(String id);
	
	public Users findByObject(String email);
}
