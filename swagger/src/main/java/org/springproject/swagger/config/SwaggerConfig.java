package org.springproject.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

//	@Bean
//	public Docket ProductApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.genericModelSubstitutes(DeferredResult.class)
//				.useDefaultResponseMessages(false)
//				.forCodeGeneration(false)
//				.pathMapping("/")
//				.select()
//				.build()
//				.apiInfo(productApiInfo());
//	}
//
//	private ApiInfo productApiInfo() {
//		ApiInfo apiInfo = new ApiInfo("XXX系统数据接口文档",
//				"文档描述。。。",
//				"1.0.0",
//				"API TERMS URL",
//				"联系人邮箱",
//				"license",
//				"license url");
//		return apiInfo;
//	}
//
//	@Bean
//	public Docket customDocket() {
//		return new Docket(DocumentationType.SWAGGER_2);
//
//	}

	@Bean
	public Docket restApi1() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("api1")
				.apiInfo(apiInfo("api1Name","v1.0"))
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.springproject.swagger.controller"))//api接口包扫描路径
//				.paths(PathSelectors.regex(".*/home/.*"))	//可以根据url路径设置哪些请求加入文档，忽略哪些请求
				.build();
	}

	@Bean
	public Docket restApi2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("接口2")
				.apiInfo(apiInfo("api2Name","v1.0"))
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.springproject.swagger.controller2"))//api接口包扫描路径
//				.paths(PathSelectors.regex(".*/user/.*"))	//可以根据url路径设置哪些请求加入文档，忽略哪些请求
				.build();
	}


	private ApiInfo apiInfo(String apiName,String version) {
		ApiInfo apiInfo = new ApiInfo("XXX系统数据接口文档",
				apiName,
				version,
				"API TERMS URL",
				"联系人邮箱",
				"license",
				"license url");
		return apiInfo;
	}
}