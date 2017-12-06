package com.datuzi.supersoft.config;

import io.swagger.annotations.Api;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfig {

    private String docVersion;
    private boolean enabled;

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createApiInfo())
                .groupName("SuperSoft")
                .enable(enabled)//只在开发环境启用
                .select()//实例化
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(java.sql.Timestamp.class, java.util.Date.class);
    }

    private ApiInfo createApiInfo(){
        return new ApiInfoBuilder()
                //.contact(new Contact("","#",""))
                .description("      <h5>移动端相关接口文档，包含请求方式、请求/响应数据类型</h5>" )
                .title("superSoft-api文档")
                .version(docVersion)
                .build();
    }

    public String getDocVersion() {
        return docVersion;
    }

    public void setDocVersion(String docVersion) {
        this.docVersion = docVersion;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
