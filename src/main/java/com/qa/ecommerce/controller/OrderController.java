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

import com.qa.ecommerce.entity.Order;
import com.qa.ecommerce.exception.OrderAlreadyExistsException;
import com.qa.ecommerce.exception.OrderNotFoundException;
import com.qa.ecommerce.service.OrderServiceImpl;

@RestController
@RequestMapping("api/v1")
public class OrderController {


	@Autowired
	OrderServiceImpl orderService;
	
	ResponseEntity<?> responseEntity;
	
	/*
	 * End Points          
	 *  getAllOrders (GET)
	 */
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders(){
		try {
			List<Order> empList = this.orderService.getAllOrders();
			responseEntity = new ResponseEntity<>(empList,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	//orders/1
	@GetMapping("/orders/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") int id) throws OrderNotFoundException{
		try {
			Order order = this.orderService.getOrderById(id);
			responseEntity = new ResponseEntity<>(order,HttpStatus.OK);
		} catch(OrderNotFoundException e) {
			throw e;
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return responseEntity;
	}
	
	
	@PostMapping("/orders")
	public ResponseEntity<?> addOrder(@RequestBody Order order) throws OrderAlreadyExistsException{
		try {
			Order addedOrder = this.orderService.addOrder(order);
			System.out.println("added order" + addedOrder);
			responseEntity = new ResponseEntity<>(order,HttpStatus.CREATED);
		} catch(OrderAlreadyExistsException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}

	
	@PutMapping("/orders")
	public ResponseEntity<?> updateOrder(@RequestBody Order order) throws OrderNotFoundException{
		try {
			Order updatedOrder = this.orderService.updateOrder(order);			
			responseEntity = new ResponseEntity<>(updatedOrder,HttpStatus.OK);
		} catch(OrderNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") int id) throws OrderNotFoundException{
		try {
			boolean status = this.orderService.deleteOrder(id);			
			if(status)
			responseEntity = new ResponseEntity<>("Order deleted Successfuly !!",HttpStatus.OK);
		} catch(OrderNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
}
