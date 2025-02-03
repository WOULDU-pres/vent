package me.hwjoo.backend.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정 적용
                .allowedOrigins("http://localhost:3000") // 프론트엔드 오리진 허용 (필요 시 추가 가능)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // 허용할 HTTP 메소드 지정
                .allowedHeaders("*") // 모든 헤더 허용
                .exposedHeaders("Content-Type", "Transfer-Encoding")  // [수정사항] 올바른 헤더 키 노출 설정
                .allowCredentials(true) // 인증 정보(쿠키 등) 포함 요청 허용
                .maxAge(3600); // preflight 요청 결과 캐시 시간 (초)
    }
}
