package com.qa.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.ecommerce.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findByEmailIdAndPassword(String emailId, String password);

	public Customer findByCustomerName(String customerName);
	
}
