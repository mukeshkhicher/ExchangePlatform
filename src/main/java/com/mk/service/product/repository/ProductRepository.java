package com.mk.service.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mk.service.product.domain.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{
	Page<Product> findByCategoryAndName(String category,String name,Pageable pageable);
	Page<Product> findByCategory(String category,Pageable pageable);
	@Query("select p from Product p where p.attributes[?1] = ?2")
	Page<Product> findByAttributeAndValue(String attribute, String value, Pageable pageable);
}
