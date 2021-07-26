package com.mk.service.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.mk.service.user.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{

	Member findByEmail(String email);

}
