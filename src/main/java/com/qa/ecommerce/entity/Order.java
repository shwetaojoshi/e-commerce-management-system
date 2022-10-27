package com.qa.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Column(name = "order_date")
	private LocalDateTime orderDate = LocalDateTime.now();
	
	private int customerId;
	
	/*
	 * @OneToOne(cascade = CascadeType.ALL,optional = false) private Customer
	 * customer;
	 */
	
	@OneToMany
	@JoinTable(name = "order_products", joinColumns =  {@JoinColumn(name= "order_id", referencedColumnName = "order_id")}, inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    })
	private List<Product> products;

	/*
	 * public Order(int orderId, LocalDateTime orderDate, Customer customer) {
	 * super(); this.orderId = orderId; this.orderDate = orderDate; this.customer =
	 * customer; }
	 */
	
	
	
	
}