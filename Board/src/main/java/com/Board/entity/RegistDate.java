package com.Board.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(value = (AuditingEntityListener.class))
@MappedSuperclass
@Getter
public class RegistDate extends RegistDateTime{
	
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;		// 등록자
	
	@LastModifiedBy
	private String modifiedBy;	// 수정자

}
