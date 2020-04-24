/*
 * Copyright (c) 2019/4/3 9:49:54
 * Created by zhuxj
 */

package com.hongtaiyang.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    /* -----------swagger2 config----------- */
    @Value("${swagger2.apiInfo.title}")
    private String title;
    @Value("${swagger2.apiInfo.version}")
    private String version;
    @Value("${swagger2.apiInfo.description}")
    private String description;
    @Value("${swagger2.apiInfo.contact.name}")
    private String name;
    @Value("${swagger2.apiInfo.contact.url}")
    private String url;
    @Value("${swagger2.apiInfo.contact.email}")
    private String email;
    @Value("${swagger2.apis.basePackage}")
    private String basePackage;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(title)
                        .version(version)
                        .description(description)
                        .contact(new Contact(name, url, email))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }
    /* -----------swagger2 config----------- */
}
