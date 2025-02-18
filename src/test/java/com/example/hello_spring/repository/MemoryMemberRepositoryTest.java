package com.example.hello_spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.hello_spring.domain.Member;

// 테스트 코드 작성
class MemoryMemberRepositoryTest {
	
	// 테스트할 레포지토리
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	// 한 테스트 함수가 끝날 때마다 데이터 초기화 필요
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		
//		System.out.println("result = " + (result == member));
//		Assertions.assertEquals(result, member);
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName(member1.getName()).get();
		
		assertThat(member1).isEqualTo(result);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> members = new ArrayList<Member>();
		members.add(member1);
		members.add(member2);
		
		List<Member> results = repository.findAll();
		
		assertThat(results).isEqualTo(members);
	}
}
