package hecore.swagger;

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
public class ViewSwagger {

	@Bean
	public Docket initRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(ai())
				.select()
				.apis(RequestHandlerSelectors.basePackage("hecore"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo ai(){
		return new ApiInfoBuilder()
				.title("hecore build Restful APIs with springboot&swagger")
				.description("my email hecore@163.com,this project url:https://github.com/hecore/springboot")
				.termsOfServiceUrl("https://github.com/hecore/springboot")
				.contact("hecore")
				.version("1.0")
				.build();
	}
}
