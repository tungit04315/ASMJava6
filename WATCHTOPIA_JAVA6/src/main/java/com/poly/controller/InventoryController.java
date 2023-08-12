package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.Inventory;
import com.poly.service.ParamService;
import com.poly.service.SessionService;
import com.poly.service_bean.InventoryService;

@Controller
public class InventoryController {

	@Autowired
	InventoryService ivenDAO;
	//kk
	@Autowired
	SessionService sService;
	@Autowired
	ParamService pService;
	
	List<Inventory> list;
	
	@RequestMapping("/admin/inventory")
	public String inventory(Model m) {
		list	= ivenDAO.findAll();
		Inventory rqItem = new Inventory();
		m.addAttribute("rqItem", rqItem);
		m.addAttribute("listInventory", list);
		
		return "manager/inventory";
	}
	// cap nhat moi
	@RequestMapping("/admin/inventory/edit")
	public String editInventory(Model m,  Inventory item) {
		list	= ivenDAO.findAll();
		int idV = pService.getInt("id", -1);
		
		Inventory inven = new Inventory();
		inven =  ivenDAO.findInvById(idV);
	
		sService.setAttribute("SessionInventory", inven);
		m.addAttribute("rqItem", inven);
		
		m.addAttribute("listInventory", list);
		System.out.println("haha");
		return "manager/inventory"; 
	}
	
	@PostMapping("/admin/inventory/update")
	public String update( Model m,@ModelAttribute("rqItem") Inventory itemInven) {
		int qty =  pService.getInt("quantity", 0);
			
			Inventory inventory = (Inventory) sService.getAttribute("SessionInventory");
			itemInven.setId(inventory.getId());
			itemInven.setProduct(inventory.getProduct());
			itemInven.setQuantity(qty);
			
			ivenDAO.update(itemInven);
			m.addAttribute("message", "Cập nhật thành công");
			list	= ivenDAO.findAll();
			
			
			m.addAttribute("rqItem", itemInven);
			m.addAttribute("listInventory", list);
		return "redirect:/admin/inventory"; 
	}
}
