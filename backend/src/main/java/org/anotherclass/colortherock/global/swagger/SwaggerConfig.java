package org.anotherclass.colortherock.global.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/**")
                .build();
    }
    @Bean
    public OpenAPI ColorTheRockOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Color The Rock API")
                        .description("Color The Rock API 명세서입니다.")
                        .version("v0.0.1"));
    }
}