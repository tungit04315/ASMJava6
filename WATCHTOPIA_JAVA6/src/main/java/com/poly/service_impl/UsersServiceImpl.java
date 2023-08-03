package com.poly.service_impl;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.jsse.PEMFile;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.poly.bean.Users;
import com.poly.dao.UsersDAO;
import com.poly.service_bean.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	UsersDAO dao;
	
	@Override
	public List<Users> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Users findById(String id) {
		Users users = dao.findById(id).orElse(null);
		if(users!=null) {
			return users;
		}else {
			return null;
		}
	}

	@Override
	public Users create(Users u) {
		return dao.save(u);
		
	}

	@Override
	public Users update(Users u) {
		return dao.save(u);
	}

	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}

	@Override
	public Users findByObject(String email) {
		return dao.findByUsersEmailObject(email);
	}
}
