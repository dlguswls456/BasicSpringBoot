package com.example.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;

	@AfterEach
	public void afterEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("hello");

		// when
		Long savedId = memberService.join(member);

		// then
		memberService.findOne(savedId).ifPresent(m -> {
			assertThat(member.getName()).isEqualTo(m.getName());
		});

	}

	@Test
	public void 중복_회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("hello");

		Member member2 = new Member();
		member2.setName("hello");

		// when, then
		memberService.join(member1);
		assertThrows(IllegalStateException.class, () -> {
			memberService.join(member2);
		});

	}
}
