package com.mk.service.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.mk.common.repository.BaseEntity;

import lombok.NonNull;

@Entity
public class Member extends BaseEntity{
	@Column(unique = true)
	private String password;
	@NonNull
	private String name;
	@Column(unique = true)
	@NonNull
	private String email;
	private String phone;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
