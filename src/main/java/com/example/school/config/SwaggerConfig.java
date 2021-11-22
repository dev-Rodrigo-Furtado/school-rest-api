package com.example.school.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${project.version}")
    private String projectVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.school"))
                .paths(PathSelectors.any())
                .build()
                .forCodeGeneration(true)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    @Bean
    public SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(authDefinition())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> authDefinition() {
        final var scopes = new AuthorizationScope[0];
        return Lists.newArrayList(SecurityReference.builder()
                .reference("JWT")
                .scopes(scopes)
                .build());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", HttpHeaders.AUTHORIZATION, ApiKeyVehicle.HEADER.getValue());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("School API")
                .description("Documentation")
                .version(projectVersion)
                .build();
    }
}
