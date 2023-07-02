package com.Board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Board.service.MemberService;

@Configuration // 설정
@EnableWebSecurity	// Spring Security를 사용하기 위한 어노테이션
public class SecurityConfig {

	@Autowired
	MemberService memberService;
	
	// 페이지의 접근에 관한 설정 (버전 변경으로 인한 람다식)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// form을 통한 로그인 설정
		http.formLogin(form -> form
		.loginPage("/member/login")		// 로그인 페이지 url설정
		.defaultSuccessUrl("/")				// 로그인 성공 시 이동 할 페이지
		.usernameParameter("loginId")			// 로그인 시 사용 할 파라메터 이름
		.passwordParameter("password")
		.loginProcessingUrl("/login")
		.failureUrl("/member/login/error"))	// 로그인 실패 시 이동 할 url
		.logout(form -> form
		.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))	// 로그아웃 url
		.logoutSuccessUrl("/"));				// 로그아웃 성공 시 이동 할 url
		
	    http
	        .authorizeHttpRequests((authz) -> authz
	            .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // 모든 사용자가 로그인(인증) 없이 접근할 수 있도록 설정
	            .requestMatchers("/main", "/member/**", "/admin/join", "/admin/new", "/find/**").permitAll() // 모든 사용자가 로그인(인증) 없이 접근할 수 있도록 설정
	            .requestMatchers("/admin/**").hasRole("ADMIN") // '/admin'으로 시작하는 경로 페이지는 role이 ADMIN인 사용자만 접근 가능하도록 설정
	            .anyRequest().authenticated()); // 그 외의 페이지는 모두 로그인(인증)을 받아야 함
	    
	    // 인증되지 않은 사용자가 리소스(페이지, 이미지 등)에 접근했을 때 설정
	    http
	        .exceptionHandling(exception -> exception
	            .authenticationEntryPoint(new AuthenticationEntryPointConfig()));

	    return http.build();
	}
	
}
