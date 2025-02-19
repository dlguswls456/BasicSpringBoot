package com.example.hello_spring.repository;

import java.util.List;
import java.util.Optional;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.mapper.MemberMapper;

public class MyBatisMemberRepository implements MemberRepository {

	// mapper 객체를 사용함
	private final MemberMapper mapper;

	public MyBatisMemberRepository(MemberMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Member save(Member member) {
		mapper.insertMember(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	public Optional<Member> findByName(String name) {
		return mapper.selectByName(name);
	}

	@Override
	public List<Member> findAll() {
		return mapper.selectAll();
	}

}
