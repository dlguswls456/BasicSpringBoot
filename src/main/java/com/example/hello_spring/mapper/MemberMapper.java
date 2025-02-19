package com.example.hello_spring.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.hello_spring.domain.Member;

@Mapper
public interface MemberMapper {

	// Member 저장
	Long insertMember(Member member);

	// 조회
	Optional<Member> selectById(Long id);

	Optional<Member> selectByName(@Param("name") String name);

	// 전체 조회
	List<Member> selectAll();
}