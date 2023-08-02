package com.poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service_bean.OrderDetailService;
import com.poly.service_bean.OrderService;



@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailSer;
	
	@RequestMapping("/order/checkout")
	public String CheckOut() {
		return "order/checkout";
	}

	@RequestMapping("/order/list")
	public String getList(Model m, HttpServletRequest request) {
		String username = request.getRemoteUser();
		m.addAttribute("orders", orderService.findByUsername(username));
		return "order/list";
	}

	@RequestMapping("/order/detail/{id}")
	public String getView(Model m, @PathVariable("id") Integer id) {
		m.addAttribute("order", orderService.findByID(id));
		m.addAttribute("detailOrder", orderDetailSer.findAllOrderDetail(id));
		return "home/homeDetail";
	}

}
