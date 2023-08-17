package com.poly.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.bean.ThongKe;
import com.poly.service.ParamService;
import com.poly.service_bean.ThongKeService;

@Controller
public class StatisticsController {
	@Autowired
	HttpServletRequest req;
	@Autowired
	
	ThongKeService tkDao;
	double tongTien = 0, doanhThu=0;
	
	@RequestMapping("/admin/index")
//	@ResponseBody
	public String showTK(Model m) {
		List<ThongKe> listTK = tkDao.getListTK();
		for (ThongKe tk : listTK) {
			tongTien= tk.getPrice()*tk.getTotal_qty();
			doanhThu+=tongTien;
		}
//		m.addAttribute("from", new Date());
//		m.addAttribute("to", new Date());
		m.addAttribute("listTK", listTK);
		m.addAttribute("doanhThu", doanhThu);
		m.addAttribute("luotMua", tkDao.getLuotMua());
		
		return "/admin/index";
	}
	
	@RequestMapping("/admin/statistic/filter")
	public String listStatisticFilter(Model m) {
		String from = req.getParameter("fromDate");
		String to = req.getParameter("toDate");
		
		List<ThongKe> listTK = tkDao.getListFilter(from, to);
		for (ThongKe tk : listTK) {
			tongTien= tk.getPrice()*tk.getTotal_qty();
			doanhThu+=tongTien;
		}
		
//		m.addAttribute("from", new Date());
//		m.addAttribute("to", new Date());
		m.addAttribute("listTK", listTK);
		m.addAttribute("doanhThu", doanhThu);
		m.addAttribute("luotMua", tkDao.getLuotMua());
		System.out.println(from + "----------" + to);
		return "/admin/index"; 
	}
}
