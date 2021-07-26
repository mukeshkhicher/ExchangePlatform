package com.mk.service.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mk.service.product.domain.Product;
import com.mk.service.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Optional<Product> getProduct(Long productId){
		return  productRepository.findById(productId);
	}
	
	@Override
	public boolean markNotAvailable(Long productId) {
		Optional<Product> product =  productRepository.findById(productId);
		if(product.isEmpty()) return false; 
		Product p = product.get();
		p.setAvailableForExchange(false);
		productRepository.save(p);
		return true;
	}

	@Override
	public Page<Product> searchByCategory(String category, Pageable pageable) {
		return productRepository.findByCategory(category, pageable);
	}

	@Override
	public Page<Product> searchByCategoryAndName(String category,String name, Pageable pageable) {
		return productRepository.findByCategoryAndName(category,name, pageable);
	}

}
