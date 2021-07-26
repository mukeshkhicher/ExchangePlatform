package com.mk.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.mk.service.user.MemberService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired MemberService memberService;
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
		// dont authenticate this particular request
		.authorizeRequests().antMatchers("/*")
		.permitAll()
//		.antMatchers("/member/register").permitAll()
//		.antMatchers(HttpMethod.GET, "/product").permitAll()
//		// all other requests need to be authenticated
//		.anyRequest().authenticated().and().userDetailsService(memberService)
//		.formLogin().and()  
//        .httpBasic()
		;
	}

}
