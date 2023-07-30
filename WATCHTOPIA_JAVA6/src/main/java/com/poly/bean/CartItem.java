package com.poly.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cart")
public class CartItem implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cart_id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "users_id")
	Users users;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	Products product;
	
	private int quantity = 1;

	public CartItem(CartItem item) {
		this.cart_id = item.getCart_id();
		this.users = item.getUsers();
		this.product = item.getProduct();
		this.quantity = 1;
	}
}
