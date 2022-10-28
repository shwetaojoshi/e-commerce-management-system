package com.qa.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.ecommerce.entity.Product;
import com.qa.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.ecommerce.exception.ProductNotFoundException;
import com.qa.ecommerce.service.ProductService;
import com.qa.ecommerce.service.ProductServiceImpl;

@RestController
@RequestMapping("api/v1")
public class ProductController {

	
	@Autowired
	ProductService productService;
	
	ResponseEntity<?> responseEntity;
	
	/*
	 * End Points          
	 *  getAllProducts (GET)
	 */
	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts(){
		try {
			List<Product> empList = this.productService.getAllProducts();
			responseEntity = new ResponseEntity<>(empList,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	//products/1
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") int id) throws ProductNotFoundException{
		try {
			Product product = this.productService.getProductById(id);
			responseEntity = new ResponseEntity<>(product,HttpStatus.OK);
		} catch(ProductNotFoundException e) {
			throw e;
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return responseEntity;
	}
	
	
	@PostMapping("/products")
	public ResponseEntity<?> addProduct(@RequestBody Product product) throws ProductAlreadyExistsException{
		try {
			Product addedProduct = this.productService.addProduct(product);
			System.out.println("added product" + addedProduct);
			responseEntity = new ResponseEntity<>(product,HttpStatus.CREATED);
		} catch(ProductAlreadyExistsException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}

	
	@PutMapping("/products")
	public ResponseEntity<?> updateProduct(@RequestBody Product product) throws ProductNotFoundException{
		try {
			Product updatedProduct = this.productService.updateProduct(product);			
			responseEntity = new ResponseEntity<>(updatedProduct,HttpStatus.OK);
		} catch(ProductNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) throws ProductNotFoundException{
		try {
			boolean status = this.productService.deleteProduct(id);			
			if(status)
			responseEntity = new ResponseEntity<>("Product deleted Successfuly !!",HttpStatus.OK);
		} catch(ProductNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
}
