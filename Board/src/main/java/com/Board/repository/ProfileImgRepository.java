package com.Board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Board.entity.Member;
import com.Board.entity.ProfileImg;

public interface ProfileImgRepository extends JpaRepository<ProfileImg, Long> {

}
