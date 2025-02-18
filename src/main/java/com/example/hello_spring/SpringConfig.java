package com.example.hello_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hello_spring.repository.JpaMemberReository;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;

import jakarta.persistence.EntityManager;

@Configuration
public class SpringConfig {

	private EntityManager em;
	
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}


	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
		return new JpaMemberReository(em);
	}
}
