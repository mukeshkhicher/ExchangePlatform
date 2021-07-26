package com.mk.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mk.service.account.AccountService;
import com.mk.service.user.domain.Member;
import com.mk.service.user.exception.MemberAlreadyExistsException;
import com.mk.service.user.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired AccountService accountService;
	@Autowired
	MemberRepository memberRepository;
	@Override
	@Transactional
	public Member register(Member member) throws MemberAlreadyExistsException {
		if(getMember(member.getEmail()) != null) throw new MemberAlreadyExistsException("Member already exists.");
		member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
		member =  memberRepository.save(member);
		accountService.createAccount(member.getId());
		return member;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = getMember(username);
		if(member == null) throw new UsernameNotFoundException("username" + username+ " not found");
		return new CustomUserDetails(member);
	}
	@Override
	public Member getMember(String email) {
		return memberRepository.findByEmail(email);
	}
}
