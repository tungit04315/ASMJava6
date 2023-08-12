package com.poly.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.ThongKe;


public interface ThongKeDAO  extends JpaRepository<ThongKe, String>{
//	@Query("select p.product_name as'name', p.product_price as'price', sum(d.quantity) as'total_qty'\r\n"
//			+ "from detail_orders d \r\n"
//			+ "inner join product p on d.product_id = p.product_id \r\n"
//			+ "group by p.product_name, p.product_price")
	
	//@Query("select new ThongKe(p.product_name, p.product_price, sum(d.quantity)) from OrderDetail d inner join Products p on d.product.product_id = p.product_id group by p.product_name, p.product_price")
	@Query("select new ThongKe(p.product_name, p.product_price, sum(d.quantity)) \r\n"
			+ "from OrderDetail d \r\n"
			+ "inner join Products p \r\n"
			+ "on d.product.product_id = p.product_id \r\n"
			+ "inner join Order r on r.orders_id = d.order.orders_id\r\n"
			+ "where r.status.status_id = 3\r\n"
			+ "group by p.product_name, p.product_price")
	List<ThongKe> getListTK();
	
	@Query("select count(o) from Order o where o.status.status_id = 3")
	Long getLuotMua();
	
	
}
