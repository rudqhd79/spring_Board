package com.Board.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public class RegistDateTime {
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regTime;			// 등록일
	
	@LastModifiedBy
	private LocalDateTime updateTime;	// 수정일

}
