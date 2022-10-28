package com.qa.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ecommerce.entity.Product;
import com.qa.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.ecommerce.exception.ProductNotFoundException;
import com.qa.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();

	}

	@Override
	public Product getProductById(int id) throws ProductNotFoundException {
		Optional<Product> findByIdOptional = this.productRepository.findById(id);
		if (!findByIdOptional.isPresent())
			throw new ProductNotFoundException();
		return findByIdOptional.get();
	}

	@Override
	public Product addProduct(Product product) throws ProductAlreadyExistsException {
		 Optional<Product> findByIdOptional = this.productRepository.findById(product.getProductId());
		 if(findByIdOptional.isPresent())
			 throw new ProductAlreadyExistsException();
		 else
			 return this.productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {
		 Optional<Product> findByIdOptional = this.productRepository.findById(product.getProductId());
		 if(!findByIdOptional.isPresent())
			 throw new ProductNotFoundException();
		 else
			 return this.productRepository.save(product);
	}

	@Override
	public boolean deleteProduct(int id) throws ProductNotFoundException {
		boolean status = false;
		Optional<Product> findByIdOptional = this.productRepository.findById(id);
		 if(!findByIdOptional.isPresent())
			 throw new ProductNotFoundException();
		 
		 this.productRepository.delete(findByIdOptional.get());
		 status = true;
		 
		 return status;
	}

	
	
}
