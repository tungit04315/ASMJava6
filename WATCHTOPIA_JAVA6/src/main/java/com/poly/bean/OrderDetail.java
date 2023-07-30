package com.poly.bean;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detail_orders", 
uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"product_id","orders_id"
		})
})

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "detailOrder_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long detailOrder_id;
	Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	Products product;
	
	@ManyToOne
	@JoinColumn(name = "orders_id")
	Order order;
}
