package com.Board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // 설정
public class EncoderConfig {

	
	// 싱글톤으로 하는 이유? ==> 일관성, 보안성, 성능을 보장하기 위해서이다
	// 비밀번호 암호화를 위한 메소드
	@Bean // @Configuration 내에서만 싱글톤이 보장된다
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
