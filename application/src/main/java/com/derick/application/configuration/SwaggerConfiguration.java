package com.derick.application.configuration;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .globalOperationParameters(globalParameterList())
// ^ here we are passing the global paramters to inlcude in swagger-ui
                .select()
                .apis( RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private List<Parameter> globalParameterList() {

        return Collections.singletonList(new ParameterBuilder()
                .name("Authorization") // name of the header
                .modelRef(new ModelRef("string")) // data-type of the header
                .required(true) // required/optional
                .parameterType("header") // for query-param, this value can be 'query'
                .description("Basic Auth Token")
                .build());
        //return Collections.singletonList(authTokenHeader);
    }

    /*
    private final ApplicationProperties properties;

    @Bean
    public Docket api() {
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .title("SpringBoot OAuth2 Example")
                .description("Support OAuth2 Server based on Database")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .ignoredParameterTypes(AuthPrincipal.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("server"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Arrays.asList(securitySchema()));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[] {
                new AuthorizationScope("read", "read all"),
                new AuthorizationScope("write", "write all")
        };

        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

    private OAuth securitySchema() {
        final List<AuthorizationScope> authorizationScopeList = new ArrayList<>(2);

        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("write", "access all"));

        final List<GrantType> grantTypes = new ArrayList<>(1);
        // 토큰 end point (http://localhost:3000/oauth/token)
        grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(properties.getSwagger().getAccessTokenUri()));

        return new OAuth("oauth2", authorizationScopeList, grantTypes);
    }
*/

    /*
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sig-Predict REST API Document")
                .description("work in progress")
                .termsOfServiceUrl("localhost")
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }
*/

}
