package com.qa.ecommerce.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.ecommerce.entity.Customer;
import com.qa.ecommerce.exception.CustomerAlreadyExistsException;
import com.qa.ecommerce.repository.CustomerRepository;
import com.qa.ecommerce.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	

	@Mock //creates the Mock Object
	private CustomerRepository customerRepository;
	
	@Autowired
	@InjectMocks
	private CustomerServiceImpl customerService;
	
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
		customer1 = new Customer(1,"customer1","customer1@gmail.com","7188787878","Adress1" ,"cust1@123");
		customer2 = new Customer(2,"customer2","customer2@gmail.com","7188787878","Adress2" ,"cust2@123");
		customer3 = new Customer(3,"customer3","customer3@gmail.com","7188787878","Adress3" ,"cust3@123");
		customerList = Arrays.asList(customer1,customer2,customer3);
	}
	
	@AfterEach
	public void tearDown() {
		customer1 = customer2 = customer3 = null;
		customerList = null;
		
	}
	
	@Test
	@DisplayName("save-customer-test")
	
	public void given_Customer_To_Save_Should_Return_The_Saved_Customer() throws CustomerAlreadyExistsException {
		when(customerRepository.findByCustomerName(any())).thenReturn(null);
		when(customerRepository.save(any())).thenReturn(customer1);
		Customer savedCustomer = customerService.saveCustomer(customer1);
		assertNotNull(savedCustomer);
		assertEquals(1,savedCustomer.getCustomerId());
		verify(customerRepository,times(1)).findByCustomerName(any());
		verify(customerRepository,times(1)).save(any());
	}
	
	@Test
	@DisplayName("save-customer-throws-exception-test")
	
	public void given_Existing_Customer_To_Save_Method_Should_Throw_Exception() throws CustomerAlreadyExistsException {
		when(customerRepository.findByCustomerName(any())).thenReturn(customer1);
		
		//customerService.saveCustomer(customer1);
		assertThrows(CustomerAlreadyExistsException.class,()-> customerService.saveCustomer(customer1));
	}
	
}
