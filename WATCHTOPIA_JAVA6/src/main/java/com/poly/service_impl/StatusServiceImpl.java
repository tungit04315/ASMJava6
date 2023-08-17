package com.poly.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Status;
import com.poly.dao.StatusDAO;
import com.poly.service_bean.StatusService;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusDAO dao;
	
	@Override
	public Status findByID(int id) {
		// TODO Auto-generated method stub
		Status stt = dao.findById(id).orElse(null);
		return stt;
		
	}

	@Override
	public String getName(int id) {
		Status stt = dao.findById(id).orElse(null);
		return stt.getStatus_name();
	}

}
