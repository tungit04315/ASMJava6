package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.bean.UserRole;

public interface UserRoleDAO extends JpaRepository<UserRole, Integer>{

}
