package com.Board.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Board.repository.ProfileImgRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileImgService {

	@Value("${profilePath}")
	private String profilePath;
	private final ProfileImgRepository profileImgRepository;
	
}
