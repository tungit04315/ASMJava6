package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service_bean.InventoryService;
import com.poly.service_bean.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	InventoryService invenService;

	@RequestMapping(value = {"/home/index","/home/watch"})
	public String GetHome(Model m) {
		m.addAttribute("items", productService.findAll());
		return "home/index";
	}
	
	@RequestMapping("/home/details/{id}")
	public String GetDetails(Model m, @PathVariable("id") Integer id) {
		m.addAttribute("item", productService.findById(id));
		m.addAttribute("inven", invenService.findByID(id));
		return "home/detailsWatch";
	}
	
	@RequestMapping("/home/cart")
	public String GetCart(Model m) {
		
		return "home/cart";
	}
	
	@RequestMapping("/home/profile")
	public String GetProfile(Model m) {
		
		return "home/profile";
	}
	
	@RequestMapping("/home/order")
	public String GetOrder(Model m) {
		
		return "home/order";
	}
}
