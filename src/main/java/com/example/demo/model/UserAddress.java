package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class UserAddress {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,updatable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	private String recipientName;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postalCode;
	private String countrty;
	private String mobile;
	
}
