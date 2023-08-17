package com.poly.service_impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.ThongKe;
import com.poly.dao.ThongKeDAO;
import com.poly.service_bean.ThongKeService;

@Service
public class ThongKeServiceImpl implements ThongKeService{

	@Autowired
	ThongKeDAO dao;
	
	@Override
	public List<ThongKe> getListTK() {
		List<ThongKe> list = dao.getListTK();
		return list;
	}

	@Override
	public Long getLuotMua() {
		Long lm = dao.getLuotMua();
		return lm;
	}

	@Override
	public List<ThongKe> getListFilter(String from, String to) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fromDate = new Date();
		Date toDate = new Date();
		try {
			fromDate = format.parse(from);
			toDate = format.parse(to);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ThongKe> list = dao.getListFilter(fromDate, toDate);
		return list;
	}

}
