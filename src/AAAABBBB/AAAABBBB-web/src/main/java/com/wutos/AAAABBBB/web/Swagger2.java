package com.wutos.AAAABBBB.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.schema.ModelRef;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {

    //第一种方式
//    @Bean
//    public Docket createRestApi() {
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        ticketPar.name("Authorization").description("token")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的ticket参数非必填，传空也可以
//        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
//        return new Docket(DocumentationType.SWAGGER_2)
//                //.groupName("AAAABBBB API DOC")
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.wutos.AAAABBBB.web.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(pars) ;
//    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("AAAABBBB API DOC")
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.wutos.AAAABBBB.web.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))//有注解@RestController的都扫描
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> list = new ArrayList();
        list.add(new ApiKey("Authorization", "Authorization", "header"));
        return list;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> list = new ArrayList();
        list.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
        return list;
    }
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> list = new ArrayList();
        list.add(new SecurityReference("Authorization", authorizationScopes));
        return list;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()

                // 页面标题
                .title("AAAABBBB restful api")
                // 创建人
                .contact("zc")
                // 版本号
                .version("2.0")
                // 描述
                .description("Basic Service API")
                .build();
    }
}
