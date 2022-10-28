package com.qa.ecommerce.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ecommerce.entity.Customer;
import com.qa.ecommerce.exception.AuthenticationException;
import com.qa.ecommerce.exception.CustomerAlreadyExistsException;
import com.qa.ecommerce.exception.CustomerNotFoundException;
import com.qa.ecommerce.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {

		return this.customerRepository.findAll();

	}

	@Override
	public Customer getCustomerById(int id) throws CustomerNotFoundException {
		Optional<Customer> findByIdOptional = this.customerRepository.findById(id);
		if (!findByIdOptional.isPresent())
			throw new CustomerNotFoundException();
		return findByIdOptional.get();
	}

	@Override
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException {
		 Optional<Customer> findByIdOptional = this.customerRepository.findById(customer.getCustomerId());
		 if(findByIdOptional.isPresent())
			 throw new CustomerAlreadyExistsException();
		 else
			 return this.customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		 Optional<Customer> findByIdOptional = this.customerRepository.findById(customer.getCustomerId());
		 if(!findByIdOptional.isPresent())
			 throw new CustomerNotFoundException();
		 else
			 return this.customerRepository.save(customer);
	}

	@Override
	public boolean deleteCustomer(int id) throws CustomerNotFoundException {
		boolean status = false;
		Optional<Customer> findByIdOptional = this.customerRepository.findById(id);
		 if(!findByIdOptional.isPresent())
			 throw new CustomerNotFoundException();
		 
		 this.customerRepository.delete(findByIdOptional.get());
		 status = true;
		 
		 return status;
	}

	@Override
	public Customer autheticate(String emailId, String password) throws AuthenticationException {
		if(Objects.isNull(emailId) && Objects.isNull(password)) {
			throw new AuthenticationException();
		}
		Customer autheticatedCustomer = this.customerRepository.findByEmailIdAndPassword(emailId, password);
		if(Objects.isNull(autheticatedCustomer)) {
			throw new AuthenticationException();
		}
		return autheticatedCustomer;
	}

	@Override
	public Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException {
		
		Customer createdCustomer = null;
		/*
		 * 1. Check whether customer already exists
		 * 2. If yes, throw CustomerAlreadyExistsException
		 * 3. If no, save customer object to database
		 * 4. Return the created customer object
		 */
		
		Customer customerByName = this.customerRepository.findByCustomerName(customer.getCustomerName());
		if(customerByName != null) {
			throw new CustomerAlreadyExistsException();
		} else {
		createdCustomer = this.customerRepository.save(customer);
		}
		
		return createdCustomer;
	}
	
	
}
