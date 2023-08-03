package com.poly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String username;
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
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "users")
	List<CartItem> Cart;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<Logs> Logs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userss")
	List<UserRole> userRoles;
}
