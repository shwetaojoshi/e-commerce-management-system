package com.qa.ecommerce.service;

import java.util.List;

import com.qa.ecommerce.entity.Product;
import com.qa.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.ecommerce.exception.ProductNotFoundException;

public interface ProductService {

	public List<Product> getAllProducts();
	public Product getProductById(int id) throws ProductNotFoundException;
	public Product addProduct(Product employee) throws ProductAlreadyExistsException;
	public Product updateProduct(Product employee) throws ProductNotFoundException;
	public boolean deleteProduct(int id) throws ProductNotFoundException;
	//public List<Product> getAllProductByDepartment(String name);
	//List<Product> findProductsByGenderAndDepartment(char gender, String department);
}
