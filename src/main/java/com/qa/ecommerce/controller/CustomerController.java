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

import com.qa.ecommerce.entity.Customer;
import com.qa.ecommerce.exception.CustomerAlreadyExistsException;
import com.qa.ecommerce.exception.CustomerNotFoundException;
import com.qa.ecommerce.service.CustomerServiceImpl;

@RestController
@RequestMapping("api/v1")
public class CustomerController {

	
	@Autowired
	CustomerServiceImpl customerService;
	
	ResponseEntity<?> responseEntity;
	
	/*
	 * End Points          
	 *  getAllCustomers (GET)
	 */
	@GetMapping("/customers")
	public ResponseEntity<?> getAllCustomers(){
		try {
			List<Customer> empList = this.customerService.getAllCustomers();
			responseEntity = new ResponseEntity<>(empList,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	//customers/1
	@GetMapping("/customers/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") int id) throws CustomerNotFoundException{
		try {
			Customer customer = this.customerService.getCustomerById(id);
			responseEntity = new ResponseEntity<>(customer,HttpStatus.OK);
		} catch(CustomerNotFoundException e) {
			throw e;
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return responseEntity;
	}
	
	
	@PostMapping("/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException{
		try {
			Customer addedCustomer = this.customerService.addCustomer(customer);
			System.out.println("added customer" + addedCustomer);
			responseEntity = new ResponseEntity<>(customer,HttpStatus.CREATED);
		} catch(CustomerAlreadyExistsException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}

	
	@PutMapping("/customers")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException{
		try {
			Customer updatedCustomer = this.customerService.updateCustomer(customer);			
			responseEntity = new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
		} catch(CustomerNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id) throws CustomerNotFoundException{
		try {
			boolean status = this.customerService.deleteCustomer(id);			
			if(status)
			responseEntity = new ResponseEntity<>("Customer deleted Successfuly !!",HttpStatus.OK);
		} catch(CustomerNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	
	/*
	 * @GetMapping("/customers/department/{name}") public ResponseEntity<?>
	 * getAllCustomersByDepartment(@PathVariable("name") String name){ try { //
	 * List<Customer> empListByDepartment =
	 * this.customerService.getAllCustomerByDepartment(name); responseEntity = new
	 * ResponseEntity<>(empListByDepartment,HttpStatus.OK); } catch(Exception e) {
	 * responseEntity = new
	 * ResponseEntity<>("some internal error occured..Please try again",HttpStatus.
	 * INTERNAL_SERVER_ERROR); }
	 * 
	 * return responseEntity; }
	 */
	 
	
	/*
	 * @GetMapping("/customers/gender/{gender}/department/{dept}") public
	 * ResponseEntity<?> findCustomersByGenderAndDepartment(@PathVariable("gender")
	 * char gender,@PathVariable("dept") String department){ try { List<Customer>
	 * empListByGenderAndDepartment =
	 * this.customerService.findCustomersByGenderAndDepartment(gender,department);
	 * responseEntity = new
	 * ResponseEntity<>(empListByGenderAndDepartment,HttpStatus.OK); }
	 * catch(Exception e) { responseEntity = new
	 * ResponseEntity<>("some internal error occured..Please try again",HttpStatus.
	 * INTERNAL_SERVER_ERROR); }
	 * 
	 * return responseEntity; }
	 */
	/*
	 * @GetMapping("/customers/total_salary") public ResponseEntity<?>
	 * findTotalSalariesOfAllCustomers(){ try { Double
	 * findTotalSalariesOfAllCustomers =
	 * this.customerService.findTotalSalariesOfAllCustomers(); responseEntity = new
	 * ResponseEntity<>(findTotalSalariesOfAllCustomers,HttpStatus.OK); }
	 * catch(Exception e) { responseEntity = new
	 * ResponseEntity<>("some internal error occured..Please try again",HttpStatus.
	 * INTERNAL_SERVER_ERROR); }
	 * 
	 * return responseEntity; }
	 */
}
