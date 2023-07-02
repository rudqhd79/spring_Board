package com.Board.entity;

import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ToString
public class Hint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="hint_id")
	private Long id;	// 힌트 식별자
	private String hintQ1;	// 힌트1
	private String hintQ2;	// 힌트2
	private String hintQ3;	// 힌트3
	private String hintQ4;	// 힌트4
	private String hintQ5;	// 힌트5
	private String hintQ6;	// 힌트6
	private String hintQ7;	// 힌트7
	private String hintQ8;	// 힌트8
	
	// ToOne으로 끝나는 관계는 항상 fetch Lazy를 해줘야 한다
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id") // 회원 엔티티의 외래 키
	private Member member;
}
