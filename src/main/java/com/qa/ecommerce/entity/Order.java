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

@Table(name = "order_details")
@Entity
public class Order {

	@Id
	@Column(name = "order_id")
	private int orderId;
	
	
	
	
	//private Customer 
	
	
	
	
	
}