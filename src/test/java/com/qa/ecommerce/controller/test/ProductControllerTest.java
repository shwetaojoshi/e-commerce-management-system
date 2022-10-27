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
import com.qa.ecommerce.controller.ProductController;
import com.qa.ecommerce.entity.Product;
import com.qa.ecommerce.service.ProductService;
import com.qa.ecommerce.service.ProductServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	@Mock
	private ProductServiceImpl productService;
	
	@Autowired
	@InjectMocks
	private ProductController productController;
	
	@Autowired
	MockMvc mockMvc;
	
	Product product1;
	Product product2;
	Product product3;
	
	List<Product> productList;
	
	@BeforeEach
	public void setUp() {
		/*
		 * Create the necessary instances
		 * Create dummy data
		 */
		product1 = new Product(11,"product1","electronics",3432,true);
		product2 = new Product(22,"product2","Home",4645,false);
		product3 = new Product(33,"product3","OutDoor",5545,true);
		productList = Arrays.asList(product1,product2,product3);
		
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@AfterEach
	public void tearDown() {
		product1 = product2 = product3 = null;
		productList = null;
		
	}
	
	@Test
	@DisplayName("add-product-test")
	public void given_Product_To_Add_Product_Should_Return_Product_As_JSON_With_Status_Created() throws Exception {
		when(productService.addProduct(any())).thenReturn(product1);
		mockMvc.perform(post("/api/v1/products")
				        .contentType(MediaType.APPLICATION_JSON)
				        .content(asJsonString(product1)))
		        .andExpect(status().isCreated())
		        .andExpect(jsonPath("$.productName").value("product1"));
	}
	
	@Test
	@DisplayName("get-product-test")
	public void given_GetAllProducts_Should_Return_List() throws Exception {
		when(productService.getAllProducts()).thenReturn(productList);
		mockMvc.perform(get("/api/v1/products")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].productName").value("product2"));
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
