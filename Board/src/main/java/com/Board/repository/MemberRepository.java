package com.Board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Board.entity.Member;

// JpaRepository는 자동으로 findBy라는 함수명을 이용하여 정보를 찾게 된다
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByLoginId(String loginId);
	
}
