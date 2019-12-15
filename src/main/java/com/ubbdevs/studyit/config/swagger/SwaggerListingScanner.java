package com.ubbdevs.studyit.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import com.ubbdevs.studyit.dto.AuthenticationDto;
import com.ubbdevs.studyit.exception.dto.ExceptionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.commons.collections4.ListUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiDescriptionBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.*;

@Component
@Builder
@AllArgsConstructor
public class SwaggerListingScanner implements ApiListingScannerPlugin {

    private static final String STRING_TYPE = "string";
    private static final String FORM_DATA_PARAMETER = "formData";

    private final CachingOperationNameGenerator operationNames;
    private final SecurityContext securityContext;
    private final TypeResolver typeResolver;

    @Override
    public List<ApiDescription> apply(final DocumentationContext context) {

        final ApiDescription desc = new ApiDescriptionBuilder(context.operationOrdering())
                .groupName("StudyIt")
                .path("/users/token")
                .description("Login with an existing user")
                .operations(authorisationOperations())
                .hidden(false)
                .build();

        return Collections.singletonList(desc);
    }


    private List<Operation> authorisationOperations() {
        return Collections.singletonList(
                new OperationBuilder(
                        operationNames)
                        .authorizations(securityContext.getSecurityReferences())
                        .tags(new HashSet<>(Collections.singletonList(SwaggerConfig.CUSTOM_OAUTH_LOGIN_CONTROLLER)))
                        .summary("Access token endpoint for client credentials grant type")
                        .codegenMethodNameStem("Login with an existing user")
                        .method(HttpMethod.POST)
                        .parameters(authorisationOperationParameters())
                        .consumes(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                        .responseMessages(responseMessages())
                        .responseModel(new ModelRef(STRING_TYPE))
                        .build()
        );
    }

    private List<Parameter> authorisationOperationParameters() {
        return ListUtils.union(mandatoryParameters(), nonMandatoryParameters());
    }

    private List<Parameter> mandatoryParameters() {
        return Arrays.asList(
                new ParameterBuilder()
                        .description("Content type")
                        .type(typeResolver.resolve(String.class))
                        .name("Content-Type")
                        .parameterType("header")
                        .required(false)
                        .defaultValue("application/x-www-form-urlencoded")
                        .modelRef(new ModelRef(STRING_TYPE))
                        .build(),
                new ParameterBuilder()
                        .description("Client id of the calling application")
                        .type(typeResolver.resolve(String.class))
                        .name("client_id")
                        .parameterType("query")
                        .required(true)
                        .modelRef(new ModelRef(STRING_TYPE))
                        .build(),
                new ParameterBuilder()
                        .description("Desired grant type (password)")
                        .type(typeResolver.resolve(String.class))
                        .name("grant_type")
                        .parameterType(FORM_DATA_PARAMETER)
                        .required(true)
                        .modelRef(new ModelRef(STRING_TYPE))
                        .build()
        );
    }

    private List<Parameter> nonMandatoryParameters() {

        return Arrays.asList(
                baseFormDataParameterBuilder()
                        .description("Email/Username of a registered user")
                        .name("username")
                        .build(),
                baseFormDataParameterBuilder()
                        .description("Password of a registered user")
                        .name("password")
                        .build()
        );
    }

    private ParameterBuilder baseFormDataParameterBuilder() {
        return new ParameterBuilder()
                .type(new TypeResolver().resolve(String.class))
                .parameterType(FORM_DATA_PARAMETER)
                .required(false)
                .modelRef(new ModelRef(STRING_TYPE));
    }

    private Set<ResponseMessage> responseMessages() {
        return Sets.newHashSet(
                new ResponseMessageBuilder()
                        .code(200)
                        .message("User successfully authenticated")
                        .responseModel(new ModelRef(AuthenticationDto.class.getSimpleName()))
                        .build(),
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad request")
                        .responseModel(new ModelRef(ExceptionDto.class.getSimpleName()))
                        .build(),
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Unsupported grant type")
                        .responseModel(new ModelRef(ExceptionDto.class.getSimpleName()))
                        .build(),
                new ResponseMessageBuilder()
                        .code(401)
                        .message("Invalid client")
                        .responseModel(new ModelRef(ExceptionDto.class.getSimpleName()))
                        .build()
        );
    }

    @Override
    public boolean supports(final DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}

