package com.jy.trim.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 在线接口调试
 *
 * @author jinchunzhao
 * @version 1.0
 * @date 2020-03-31 9:16
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {


    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("1.0")
                .select()
                //这里指定Controller扫描包路径(项目路径也行)
                .apis(RequestHandlerSelectors.basePackage("com.jy.trim.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    /**
     * API 说明
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("trim-space API").description(" trim-space 脚手架 ").termsOfServiceUrl("http://localhost/")
                .contact(new Contact("jinchunzhao", "http://localhost/", "459147801@qq.com")).version("1.0").build();
    }
}
