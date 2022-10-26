package com.qa.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "customer_details")
@Entity
public class Customer {

	@Id
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "customer_email")
	private String emailId;
	
	@Column(name = "customer_contact_number")
	private int CustomerContactNumber;
	
	@Column(name = "customer_address")
	private String customerAddress;
	
	
	
	
}
