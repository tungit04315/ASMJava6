package com.poly.bean;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "product")
public class Products implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	private String product_name;
	private String product_img;
	private String product_describe;
	private double product_price;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "brands_id")
	Branch branch;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "types_id")
	ProductType type;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetail;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<CartItem> cart;
}
