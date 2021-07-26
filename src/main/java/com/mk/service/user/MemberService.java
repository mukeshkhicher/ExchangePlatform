package com.mk.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mk.service.user.domain.Member;
import com.mk.service.user.exception.MemberAlreadyExistsException;

public interface MemberService extends UserDetailsService{
	public Member register(Member member) throws MemberAlreadyExistsException;
	Member getMember(String email);
}
