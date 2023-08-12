package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.Order;
import com.poly.service_bean.OrderService;

@Controller
public class AdminController {
	
	@Autowired
	OrderService orderService;

//	@RequestMapping("/admin/index")
//	public String getHome(Model m) {
//		return "admin/index";
//	}

//	@RequestMapping("/admin/add")
//	public String getAdd(Model m) {
//		return "manager/addProduct";
//	}
//
//	@RequestMapping("/admin/update")
//	public String getUpdate(Model m) {
//		return "manager/updateProduct";
//	}

//	@RequestMapping("/admin/inventory")
//	public String getInventory(Model m) {
//		return "manager/inventory";
//	}

//	@RequestMapping("/admin/list")
//	public String getList(Model m) {
//		return "manager/listProduct";
//	}

//	@RequestMapping("/admin/user")
//	public String getUser(Model m) {
//		return "manager/listUser";
//	}

//	@RequestMapping("/admin/history")
//	public String getHistory(Model m) {
//		return "manager/loginHistory";
//	}
	
	@RequestMapping("/admin/order")
	public String getOrder(Model m) {
		m.addAttribute("listOrder", orderService.findAllOrder());
		
		return "manager/order";
	}
	
	@RequestMapping("/admin/order/listOrdCancel")
	public String getOrderCancel(Model m) {
		m.addAttribute("listOrder", orderService.getListOrderCancelled());
		
		return "manager/order";
	}
	
	@RequestMapping("/admin/order/update/{id}")
	public String changeStatus(Model m, @PathVariable("id") Integer id) {
		Order ord = orderService.findByID(id);
		String mess = "";
		String color ="";
		if(ord != null ) {
			orderService.changeStatus(id);
			mess="Cập nhật trạng thái thành công";
			color ="text-success";
		}else {
			mess = "Cập nhật trạng thái thất bại";
			color ="text-danger";
		}
		m.addAttribute("message", mess);
		m.addAttribute("color", color);
		
		m.addAttribute("listOrder", orderService.findAllOrder());
		return "manager/order";
	}
	
	@RequestMapping("/admin/order/detele/{id}")
	public String cancelOrder(Model m, @PathVariable("id") Integer id) {
		Order ord = orderService.findByID(id);
		String mess = "";
		String color ="";
		if(ord != null ) {
			orderService.cancelOrder(id);
			mess="Hủy đơn hàng thành công";
			color ="text-success";
		}else {
			mess = "Hủy đơn hàng thất bại";
			color ="text-danger";
		}
		m.addAttribute("message", mess);
		m.addAttribute("color", color);
		
		m.addAttribute("listOrder", orderService.findAllOrder());
		return "manager/order";
	}
	
	
	
	@RequestMapping("/admin/notification")
	public String getNotification(Model m) {
		return "manager/notification";
	}
}
