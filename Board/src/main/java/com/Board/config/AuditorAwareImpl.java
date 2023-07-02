package com.Board.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// 로그인한 사용자의 정보를 등록자와 수정자로 지정한다 (implements를 한 클래스는 자동센서를 인식하는 것과 같다)
public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// Authentication은 현재 로그인한 사용자의 정보를 가져온다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String loginId = "";
		
		// 로그인한 사용자의 정보가 있으면
		if (authentication != null) {
			// 사용자의 이름을 가져온다
			loginId = authentication.getName();
		}
		return Optional.of(loginId);
	}

}
