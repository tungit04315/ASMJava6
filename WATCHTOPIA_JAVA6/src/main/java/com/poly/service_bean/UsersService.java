package com.poly.service_bean;

import java.util.List;

import com.poly.bean.Users;


public interface UsersService {

	public List<Users> findAll();

	public Users findById(Integer id);

	public Users create(Users u);

	public Users update(Users u);

	public void delete(Integer id);
}
