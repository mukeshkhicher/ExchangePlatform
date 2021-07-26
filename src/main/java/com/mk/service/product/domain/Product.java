package com.mk.service.product.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import com.mk.common.repository.BaseEntity;

@Entity
public class Product extends BaseEntity{
	private String category;
	private String name;
	private String description;
	private BigDecimal price;
	private boolean isAvailableForExchange;
	private Long ownerId;
	@ElementCollection
	private Map<String, String> attributes = new HashMap<>();
	public Product() {
	}
	public Product(String name, String category, String description, BigDecimal price) {
		this.name=name;
		this.category=category;
		this.description=description;
		this.price=price;
		this.isAvailableForExchange=true;
	}
	public boolean isAvailableForExchange() {
		return isAvailableForExchange;
	}
	public void setAvailableForExchange(boolean isAvailableForExchange) {
		this.isAvailableForExchange = isAvailableForExchange;
	}
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttribute(String name, String value) {
		if (value == null) {
			this.attributes.remove(value);
		} else {
			this.attributes.put(name, value);
		}
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
}
