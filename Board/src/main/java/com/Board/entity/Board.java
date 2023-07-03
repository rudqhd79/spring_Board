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
import jakarta.persistence.Table;
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
	
	// 조회 수 기본값을 0으로 지정
	@Column(columnDefinition = "integer default0", nullable=false)
	private int views;		// 게시글 조회수
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "board")
	private List<Post> posts;
	
	// 조회수 증가
	public void increaseViews() {
		this.views ++;
	}
	
	public Board createPostByBoard(String post_detail, String boardTitle) {
		Post post = Post.createPost(post_detail);
		this.addPosts(post);
		this.title = boardTitle;
		return this;
	}
	
	public void addPosts(Post post) {
		this.posts.add(post);
		post.setBoard(this);
	}
}
