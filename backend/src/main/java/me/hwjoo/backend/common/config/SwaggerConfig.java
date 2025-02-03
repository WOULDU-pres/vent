// src/main/java/me/hwjoo/backend/common/config/SwaggerConfig.java
package me.hwjoo.backend.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("vent-api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI ventOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("VENT API")
                        .description("VENT 이벤트 관리 시스템 API 문서")
                        .version("1.0.0"));
    }
}
