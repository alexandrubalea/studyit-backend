package com.ubbdevs.studyit.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.ubbdevs.studyit.dto.AuthenticationDto;
import com.ubbdevs.studyit.exception.dto.ExceptionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    public static final String CUSTOM_OAUTH_LOGIN_CONTROLLER = "Users";
    private static final String SPRING_OAUTH = "SPRING_OAUTH";
    private final String swaggerOauthEndpoint;

    private TypeResolver typeResolver;

    public SwaggerConfig(@Value("${swagger.oauth.endpoint}") final String swaggerOauthEndpoint, final TypeResolver typeResolver) {
        this.swaggerOauthEndpoint = swaggerOauthEndpoint;
        this.typeResolver = typeResolver;
    }

    @Bean
    public Docket swaggerApi(final SecurityContext securityContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("StudyIt")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ubbdevs.studyit.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(ZoneId.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .additionalModels(typeResolver.resolve(AuthenticationDto.class))
                .additionalModels(typeResolver.resolve(ExceptionDto.class))
                .tags(new Tag(CUSTOM_OAUTH_LOGIN_CONTROLLER, "Users API"))
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext));
    }

    @Bean
    public ApiListingScannerPlugin listingScanner(final CachingOperationNameGenerator operationNameGenerator) {
        return new SwaggerListingScanner(operationNameGenerator, securityContext(), typeResolver);
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
//                .clientId(SWAGGER_CLIENT_ID)
                .build();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("StudyIt API Documentation")
                .description("StudyIt application")
                .version("1.0")
                .build();
    }

    private SecurityScheme securityScheme() {
        final GrantType passwordGrantType = new ResourceOwnerPasswordCredentialsGrant(swaggerOauthEndpoint);

        return new OAuthBuilder().name(SPRING_OAUTH)
                .grantTypes(Collections.singletonList(passwordGrantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{};
    }

    @Bean
    public SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Collections.singletonList(new SecurityReference(SPRING_OAUTH, scopes())))
                .build();
    }
}
