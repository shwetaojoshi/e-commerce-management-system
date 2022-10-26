package com.qa.ecommerce.service;

import java.util.List;

import com.qa.ecommerce.entity.Order;
import com.qa.ecommerce.exception.OrderAlreadyExistsException;
import com.qa.ecommerce.exception.OrderNotFoundException;

public interface OrderService {

	
	public List<Order> getAllOrders();
	public Order getOrderById(int id) throws OrderNotFoundException;
	public Order addOrder(Order employee) throws OrderAlreadyExistsException;
	public Order updateOrder(Order employee) throws OrderNotFoundException;
	public boolean deleteOrder(int id) throws OrderNotFoundException;
	//public List<Order> getAllOrderByDepartment(String name);
	//List<Order> findOrdersByGenderAndDepartment(char gender, String department);
}
