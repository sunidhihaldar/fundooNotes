package com.bridgelabz.fundooNotes.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.bridgelabz.fundooNotes.controller")).paths(regex("/.*"))                          
          .build();
          //.apiInfo(new ApiInfo("Fundoo Notes App", "Contains Apis for registerting, logging in and creating notes and labels", "1.0", "http://www.apache.org/licenses/LICENSE-2.0", new Contact("fundoo app", "vv", "jk"), "hn", "fbg", null));
    }
}