package com.Board.entity;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import com.Board.dto.PostDto;

import lombok.ToString;
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

@Entity
@Getter
@Setter
@ToString
public class Post extends RegistDate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="post_id")
	private Long id;	// 게시글 식별자
	
	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String post_detail;	// 게시글 내용
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;
	
	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

	@OneToMany(mappedBy = "post")
	private List<PostImg> postImgs;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public void updatePost(PostDto postDto) {
		this.post_detail = postDto.getPost_detail();
	}
	
	public static Post createPost(String detail) {
		Post post = new Post();
		post.setPost_detail(detail);
		return post;
	}
	
	public PostDto updateDto(Post post) {
		return modelMapper.map(post, PostDto.class);
	}
	
}
