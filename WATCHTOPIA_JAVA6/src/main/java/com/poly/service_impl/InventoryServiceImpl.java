package com.poly.service_impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Inventory;
import com.poly.dao.InventoryDAO;
import com.poly.service_bean.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	InventoryDAO dao;
	
	@Override
	public List<Inventory> findAll() {
		return dao.findAll();
	}

	@Override
	public Inventory findByID(Integer id) {
		return dao.findObject(id);
	}

	@Override
	public Inventory create(Inventory inventory) {
		return dao.save(inventory);
	}

	@Override
	public Inventory update(Inventory inventory) {
		return dao.save(inventory);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Inventory findInvById(int iid) {
		Inventory iven = dao.findBfindById(iid);
		return iven;
	}

	@Override
	public void save(Inventory inv) {
		// TODO Auto-generated method stub
		dao.save(inv);
	}

	@Override
	public Inventory findInventoryByIdProduct(Integer id) {
		// TODO Auto-generated method stub
		return dao.findObjectInventory(id);
	}

}
