package com.Board.entity;

import java.util.List;

import com.Board.dto.BoardDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Board extends RegistDate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="board_id")
	private Long id;		// 게시판 식별자
	private String title;	// 게시글 제목
	private int views;		// 게시글 조회수
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "board")
	private List<Post> posts;
	
	public void updateBoard (BoardDto boardDto) {
		
	}
}
