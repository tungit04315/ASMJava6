package com.poly.service_bean;


import com.poly.bean.Status;

public interface StatusService {

	public Status findByID(int id);
	
	public String getName(int id);
}
