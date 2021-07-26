package com.mk.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.service.user.domain.Member;
import com.mk.service.user.exception.MemberAlreadyExistsException;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired 
	MemberService memberService;
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Member member){
		try {
			return new ResponseEntity<Member>(memberService.register(member),HttpStatus.OK);
		} catch (MemberAlreadyExistsException e) {
			return new ResponseEntity<>("Member already exists",HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
