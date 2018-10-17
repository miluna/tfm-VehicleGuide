package com.miluna.springvehicleguide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Creates Swagger 2
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Creates api info for swagger 2
     */
    private ApiInfo apiInfo() {

        return new ApiInfo(
                "title",
                "description",
                "version",
                "termsOfServiceUrl",
                new Contact ("name", "http://www.yourdomain.com/", "email@email.com"),
                "license",
                "licenseUrl");
    }
}