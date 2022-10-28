package com.sfin.sspareport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class ConfigSwagger {

    @Bean
    public Docket postApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("Report").select()
                .apis(RequestHandlerSelectors.basePackage("com.sfin.sspareport.controller")).build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Report API").description("reporting sspa").build();
    }
}
