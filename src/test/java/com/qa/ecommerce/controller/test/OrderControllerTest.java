package com.qa.ecommerce.controller.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.qa.ecommerce.controller.OrderController;
import com.qa.ecommerce.entity.Order;
import com.qa.ecommerce.entity.Product;
import com.qa.ecommerce.service.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	@Mock
	private OrderServiceImpl orderService;
	
	@Autowired
	@InjectMocks
	private OrderController orderController;
	
	@Autowired
	MockMvc mockMvc;
	
	Order order1;
	Order order2;
	Order order3;
	
	List<Order> orderList;
	
	@BeforeEach
	public void setUp() {
		/*
		 * Create the necessary instances
		 * Create dummy data
		 */
		
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		products.add(new Product());
		
		
		
		order1 = new Order(11, LocalDateTime.now(),1,products);
		order2 = new Order(22,LocalDateTime.now(),2,products);
		order3 = new Order(33,LocalDateTime.now(),3,products);
		orderList = Arrays.asList(order1,order2,order3);
		
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}
	
	@AfterEach
	public void tearDown() {
		order1 = order2 = order3 = null;
		orderList = null;
		
	}
	
	@Test
	@DisplayName("add-order-test")
	public void given_Order_To_Add_Order_Should_Return_Order_As_JSON_With_Status_Created() throws Exception {
		when(orderService.addOrder(any())).thenReturn(order1);
		mockMvc.perform(post("/api/v1/orders")
				        .contentType(MediaType.APPLICATION_JSON)
				        .content(asJsonString(order1)))
		        .andExpect(status().isCreated())
		        .andExpect(jsonPath("$.orderId").value(11));
	}
	
	@Test
	@DisplayName("get-order-test")
	public void given_GetAllOrders_Should_Return_List() throws Exception {
		when(orderService.getAllOrders()).thenReturn(orderList);
		mockMvc.perform(get("/api/v1/orders")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].orderId").value(22));
	}

	
	public static String asJsonString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());
		String jsonStr = null;
		 
        // Try block to check for exceptions
        try {
 
            // Getting organisation object as a json string
            jsonStr = mapper.writeValueAsString(obj);
 
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

