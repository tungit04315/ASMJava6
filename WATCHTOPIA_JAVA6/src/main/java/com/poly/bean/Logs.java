package com.poly.bean;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Logs")
public class Logs implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int log_id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "users_id")
	Users user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "login_time")
	private Date login_time = new Date();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "login_out")
	private Date login_out = new Date();
}
