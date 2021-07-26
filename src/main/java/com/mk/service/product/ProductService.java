package com.mk.service.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mk.service.product.domain.Product;

public interface ProductService {
	public Product addProduct(Product product);
	public Page<Product> searchByCategory(String category,Pageable pageable);
	public Page<Product> searchByCategoryAndName(String catergory,String name,Pageable pageable);
	boolean markNotAvailable(Long productId);
	public Optional<Product> getProduct(Long productId);
}
