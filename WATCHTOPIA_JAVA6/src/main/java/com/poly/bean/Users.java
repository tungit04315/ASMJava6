package com.poly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "users")

public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int users_id;
	private String fullname;
	private String passwords;
	private String email;
	private String phone;
	private boolean active = true;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_login")
	private Date createdate = new Date();
	
	private int failed_login_attempts = 0;
	private boolean blocked = false;
	private boolean roles = false;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "users")
	List<CartItem> Cart;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<Logs> Logs;
}
