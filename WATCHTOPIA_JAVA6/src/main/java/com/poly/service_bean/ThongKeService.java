package com.poly.service_bean;

import java.util.List;

import com.poly.bean.ThongKe;

public interface ThongKeService {
	public List<ThongKe> getListTK();
	public List<ThongKe> getListFilter(String from, String to);
	public Long getLuotMua();
}
