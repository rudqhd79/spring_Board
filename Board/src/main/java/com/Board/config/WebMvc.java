package com.Board.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @WEbMvcConfigurer는 경로나 매핑을 설정 할 수 있다
@Configuration
public class WebMvc implements WebMvcConfigurer {
	
	// @Value는 경로 값을 가져온다
	// autowired가 주입되지 않아 직접 application.properties에 있는 값을 가져왔다
	// application properties에서 경로 인식이 안되면 직접 입력하자 ${uploadPath:C:\\Board}
	@Value("${uploadPath}")
	String uploadPath;

	// 정적 리소스의 위치와 요청 경로간의 매핑 설정 가능
	// 정적 (static)의 파일들의 매핑 설정이다
	// "/resources/**"는 보안적인 면에서 따로 추가하지 않았다.
	// 위의 설정은 /images/** 패턴으로 요청된 URL에 대해 /src/, /resources/, 그리고
	// uploadPath 경로에 있는 리소스 파일들을 찾아서 반환한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:/src/", "/resources/") // 절대 경로보단 상대 경로로
		.addResourceLocations(uploadPath);
	}
}
