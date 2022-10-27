package com.qa.ecommerce.controller.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.ecommerce.controller.CustomerController;
import com.qa.ecommerce.entity.Customer;
import com.qa.ecommerce.service.CustomerService;
import com.qa.ecommerce.service.CustomerServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

	@Mock
	private CustomerServiceImpl customerService;
	
	@Autowired
	@InjectMocks
	private CustomerController customerController;
	
	@Autowired
	MockMvc mockMvc;
	
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
		customer1 = new Customer(11,"customer1","customer1@gmail.com","34523432","address1" , "pass1");
		customer2 = new Customer(22,"customer2","customer2@gmail.com","45645645","address2" , "pass2");
		customer3 = new Customer(33,"customer3","customer3@gmail.com","55645645","address3" , "pass3");
		customerList = Arrays.asList(customer1,customer2,customer3);
		
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@AfterEach
	public void tearDown() {
		customer1 = customer2 = customer3 = null;
		customerList = null;
		
	}
	
	@Test
	@DisplayName("add-customer-test")
	public void given_Customer_To_Add_Customer_Should_Return_Customer_As_JSON_With_Status_Created() throws Exception {
		when(customerService.addCustomer(any())).thenReturn(customer1);
		mockMvc.perform(post("/api/v1/customers")
				        .contentType(MediaType.APPLICATION_JSON)
				        .content(asJsonString(customer1)))
		        .andExpect(status().isCreated())
		        .andExpect(jsonPath("$.customerName").value("customer1"));
	}
	
	@Test
	@DisplayName("get-customer-test")
	public void given_GetAllCustomers_Should_Return_List() throws Exception {
		when(customerService.getAllCustomers()).thenReturn(customerList);
		mockMvc.perform(get("/api/v1/customers")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].customerName").value("customer2"));
	}

	
	public static String asJsonString(Object obj) {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
		 
        // Try block to check for exceptions
        try {
 
            // Getting organisation object as a json string
            jsonStr = Obj.writeValueAsString(obj);
 
            // Displaying JSON String on console
            System.out.println(jsonStr);
        }
 
        // Catch block to handle exceptions
        catch (IOException e) {
 
            // Display exception along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
        return jsonStr;
	}

}
