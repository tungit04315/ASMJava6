package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/home/index")
	public String GetHome(Model m) {
		
		return "home/index";
	}
	
	@RequestMapping("/home/details")
	public String GetDetails(Model m) {
		
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
