package com.Board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 이 클래스는 Audit(감사) Configuration(설정)이다
// Audit을 위한 SpringSecurity
@Configuration
@EnableJpaAuditing
public class AuditConfig {
	
	// 이 메소드는 로그인한 사용자를 감지하기 위한 메소드이다. (자동 센서)
	@Bean // 싱글톤이 보장되지만 @Configuration이 적용된 범위 내에서만 적용된다
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}

}
