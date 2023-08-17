package com.poly.service_impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Order;
import com.poly.bean.OrderDetail;
import com.poly.bean.Status;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.service_bean.OrderService;
import com.poly.service_bean.StatusService;


@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;

	@Autowired
	StatusService sttDao;
	
	@Autowired
	OrderDetailDAO ddao;
	
	
	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Order order = mapper.convertValue(orderData, Order.class);
		
		dao.save(order);

		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
				.peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(details);

		return order;

	}

	@Override
	public List<Order> findByUsername(String email) {
		return dao.findByUsername(email);
	}

	@Override
	public List<Order> findAllOrder() {
		// TODO Auto-generated method stub
//		return dao.findAllOrder();
		return dao.findAll();
	}

	@Override
	public Order findByID(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public void changeStatus(int id) {
		Order ord = dao.findById(id).orElse(null);
		
		if(ord.getStatus().getStatus_id()==1) {
			Status stt = sttDao.findByID(id+1);
			ord.setStatus(stt);
		}else if(ord.getStatus().getStatus_id()==2) {
			Status stt = sttDao.findByID(id+1);
			ord.setStatus(stt);
		}else if(ord.getStatus().getStatus_id()==3) {
			
		}
		
		dao.save(ord);
	}

	@Override
	public void cancelOrder(int id) {
		Order ord = dao.findById(id).orElse(null);
		if(ord != null) {
			Status stt = new Status();
			stt.setStatus_id(4);
			stt.setStatus_name("Da huy");
			ord.setStatus(stt);
			dao.save(ord);
		}
		
	}

	@Override
	public List<Order> getListOrderCancelled() {
		return dao.getListOrderCancelled();
	}


}
