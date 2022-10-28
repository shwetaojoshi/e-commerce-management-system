package com.qa.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ecommerce.entity.Order;

import com.qa.ecommerce.exception.OrderAlreadyExistsException;
import com.qa.ecommerce.exception.OrderNotFoundException;

import com.qa.ecommerce.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrders() {
		return this.orderRepository.findAll();

	}

	@Override
	public Order getOrderById(int id) throws OrderNotFoundException {
		Optional<Order> findByIdOptional = this.orderRepository.findById(id);
		if (!findByIdOptional.isPresent())
			throw new OrderNotFoundException();
		return findByIdOptional.get();
	}

	@Override
	public Order addOrder(Order order) throws OrderAlreadyExistsException {
		 Optional<Order> findByIdOptional = this.orderRepository.findById(order.getOrderId());
		 if(findByIdOptional.isPresent())
			 throw new OrderAlreadyExistsException();
		 else
			 return this.orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order) throws OrderNotFoundException {
		 Optional<Order> findByIdOptional = this.orderRepository.findById(order.getOrderId());
		 if(!findByIdOptional.isPresent())
			 throw new OrderNotFoundException();
		 else
			 return this.orderRepository.save(order);
	}

	@Override
	public boolean deleteOrder(int id) throws OrderNotFoundException {
		boolean status = false;
		Optional<Order> findByIdOptional = this.orderRepository.findById(id);
		 if(!findByIdOptional.isPresent())
			 throw new OrderNotFoundException();
		 
		 this.orderRepository.delete(findByIdOptional.get());
		 status = true;
		 
		 return status;
	}

	
	
}
