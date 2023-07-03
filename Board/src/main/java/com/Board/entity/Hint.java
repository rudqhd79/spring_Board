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
	
	// 관리자 계정만 질문 변경 가능)
	// select box에서 질문을 생성하자
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="hint_id")
	private Long id;	// 힌트 식별자
	private String hintQ;	// 힌트
}
