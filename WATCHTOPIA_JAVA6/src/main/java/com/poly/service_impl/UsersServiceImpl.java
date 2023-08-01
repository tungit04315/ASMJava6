package com.poly.service_impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Users;
import com.poly.dao.UsersDAO;
import com.poly.service_bean.UsersService;

@Service
public class UsersServiceImpl implements UsersService{


	@Autowired
	UsersDAO dao;
	
	@Override
	public List<Users> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Users findById(Integer id) {
		return dao.findById(id).get();
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
	public void delete(Integer id) {
		dao.deleteById(id);
	}



}
