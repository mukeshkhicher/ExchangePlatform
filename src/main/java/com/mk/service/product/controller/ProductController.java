package com.mk.service.product.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.service.product.ProductService;
import com.mk.service.product.domain.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product){
		try {
			return new ResponseEntity<>(productService.addProduct(product),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/:id")
	public ResponseEntity<Optional<Product>> getProducts(@PathVariable("id") Long productId) {
		try {
			return new ResponseEntity<Optional<Product>>(productService.getProduct(productId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@GetMapping
	public ResponseEntity<Page<Product>> getProducts(@Param("category") String category,@Param("name") String name, Pageable pageable) {
		try {
			if(StringUtils.hasText(name))
				return new ResponseEntity<Page<Product>>(productService.searchByCategoryAndName(category,name, pageable), HttpStatus.OK);
			return new ResponseEntity<Page<Product>>(productService.searchByCategory(category, pageable), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Page<Product>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
