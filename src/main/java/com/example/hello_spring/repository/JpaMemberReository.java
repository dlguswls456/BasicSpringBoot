package com.example.hello_spring.repository;

import java.util.List;
import java.util.Optional;

import com.example.hello_spring.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaMemberReository implements MemberRepository {

	private final EntityManager em;

	public JpaMemberReository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		log.info("JPA");
		
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member as m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
		
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		// 객체를 대상으로 쿼리를 날림
		return em.createQuery("select m from Member as m", Member.class).getResultList();
	}

}
