package com.example.hello_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;

// 서비스
// 레포지토리와 도메인을 활용해서 '비즈니스 로직' 작성
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	// 외부에서 넣어주도록 함
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * 회원가입
	 * 
	 * @param member
	 * @return
	 */
	public Long join(Member member) {
		// 같은 이름이 있는 중복 회원x -> 비즈니스 로직
		validateDuplicateMember(member);

		memberRepository.save(member);
		return member.getId();
	}

	/**
	 * 전체 회원 조회
	 * 
	 * @return
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	/**
	 * 특정 멤버 조회
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Member> findOne(Long id) {
		return memberRepository.findById(id);
	}

	/**
	 * 중복 회원 확인
	 * 
	 * @param member
	 */
	private void validateDuplicateMember(Member member) {
		Optional<Member> result = memberRepository.findByName(member.getName());
		result.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
}
