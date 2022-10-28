package com.qa.ecommerce.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qa.ecommerce.entity.Customer;
import com.qa.ecommerce.repository.CustomerRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class CustomerRepositoryTest {

	
	@Autowired
	CustomerRepository customerRepository;

	Customer customer1;
	Customer customer2;
	Customer customer3;
	
	List<Customer> customerList;
	
	@BeforeEach
	public void setUp() {
		/*
		 * Create the necessary instances
		 * Create dummy data
		 */
		customer1 = new Customer(1,"customer1","customer1@gmail.com","7188787878","Adress1" ,"Cust1@123");
		customer2 = new Customer(2,"customer2","customer2@gmail.com","7188787878","Adress2" ,"Cust2@123");
		customer3 = new Customer(3,"customer3","customer3@gmail.com","7188787878","Adress3" ,"Cust3@123");
		customerList = Arrays.asList(customer1,customer2,customer3);
	}
	
	@AfterEach
	public void tearDown() {
		customer1 = customer2 = customer3 = null;
		customerRepository.deleteAll();
		customerList = null;
		
	}
	
	@Test
	@DisplayName("save-customer-test")
	
	public void given_Customer_To_Save_Should_Return_The_Saved_Customer() {
		
		Customer savedCustomer = customerRepository.save(customer1);
		assertNotNull(savedCustomer);
		assertEquals("customer1", savedCustomer.getCustomerName());
	}
	
	@Test
	@DisplayName("get-customer-list-test")
	public void given_GetAllCustomers_Should_Return_Customer_List() {
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		customerRepository.save(customer3);
		
		List<Customer> customerList = customerRepository.findAll();
		assertEquals(3, customerList.size(),"customer list size should be 3");
		assertEquals("customer2",customerList.get(1).getCustomerName());
	}
	
	@Test
	@DisplayName("get-customer-non-existing-id-test")
	//@Disabled
	public void given_Non_Existing_Id_Should_Return_Optional_Empty() {
		customerRepository.save(customer1);
		assertThat(customerRepository.findById(22)).isEmpty();
	}
}
