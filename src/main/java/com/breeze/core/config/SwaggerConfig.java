package com.breeze.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("!product")
public class SwaggerConfig {

    @Bean
    public Docket frontApi() {
        return getDocket("front-api", "/front/**");
    }

    @Bean
    public Docket adminApi() {
        return getDocket("admin-api", "/admin/**");
    }

    private Docket getDocket(String groupName, String pathPattern) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.breeze"))
                .paths(PathSelectors.ant(pathPattern))
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Sample Service API Document")
                .description("Sample Service API based on Spring Boot")
                .version("1.0")
                .contact(new Contact("Breeze", "", "chonghwi0@gmail.com"))
                .build();
    }
}
