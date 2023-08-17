package com.poly.dao;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.bean.ThongKe;


public interface ThongKeDAO  extends JpaRepository<ThongKe, String>{
//	@Query("select p.product_name as'name', p.product_price as'price', sum(d.quantity) as'total_qty'\r\n"
//			+ "from detail_orders d \r\n"
//			+ "inner join product p on d.product_id = p.product_id \r\n"
//			+ "group by p.product_name, p.product_price")
	
	@Query("select new ThongKe(p.product_name, p.product_price, sum(d.quantity)) from OrderDetail d inner join Products p on d.product.product_id = p.product_id group by p.product_name, p.product_price")
	List<ThongKe> getListTK();
	
	@Query("select new ThongKe(p.product_name, p.product_price, sum(d.quantity)) from OrderDetail d inner join Products p on d.product.product_id = p.product_id inner join Order o on o.orders_id = d.order.orders_id where o.orders_time between :fromDate and :toDate group by p.product_name, p.product_price")
	List<ThongKe> getListFilter(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
	
	@Query("select count(o) from Order o")
	Long getLuotMua();
	
	
}
