package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping("/admin/index")
	public String getHome(Model m) {
		return "admin/index";
	}

	@RequestMapping("/admin/add")
	public String getAdd(Model m) {
		return "manager/addProduct";
	}

	@RequestMapping("/admin/update")
	public String getUpdate(Model m) {
		return "manager/updateProduct";
	}

	@RequestMapping("/admin/inventory")
	public String getInventory(Model m) {
		return "manager/inventory";
	}

	@RequestMapping("/admin/list")
	public String getList(Model m) {
		return "manager/listProduct";
	}

	@RequestMapping("/admin/user")
	public String getUser(Model m) {
		return "manager/listUser";
	}

	@RequestMapping("/admin/history")
	public String getHistory(Model m) {
		return "manager/loginHistory";
	}
	
	@RequestMapping("/admin/order")
	public String getOrder(Model m) {
		return "manager/order";
	}
	
	@RequestMapping("/admin/notification")
	public String getNotification(Model m) {
		return "manager/notification";
	}
}
