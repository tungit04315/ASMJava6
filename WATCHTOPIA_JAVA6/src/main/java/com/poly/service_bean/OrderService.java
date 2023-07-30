package com.poly.service_bean;

import java.util.List;

import com.poly.bean.Order;

public interface OrderService {

	public List<Order> findAll();
	
	public Order findByID(Long id);
	
	
}
