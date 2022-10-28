package com.qa.ecommerce.service;

import java.util.List;

import com.qa.ecommerce.entity.Customer;
import com.qa.ecommerce.exception.AuthenticationException;
import com.qa.ecommerce.exception.CustomerAlreadyExistsException;
import com.qa.ecommerce.exception.CustomerNotFoundException;

public interface CustomerService {

	
	public Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException;
	public List<Customer> getAllCustomers();
	public Customer getCustomerById(int id) throws CustomerNotFoundException;
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	public boolean deleteCustomer(int id) throws CustomerNotFoundException;
	public Customer autheticate(String emailId, String password) throws AuthenticationException;
	//public List<Customer> getAllCustomerByDepartment(String name);
	//List<Customer> findCustomersByGenderAndDepartment(char gender, String department);
	
}
