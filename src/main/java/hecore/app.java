
/**
 * @author Administrator
 *
 */
package hecore;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//E:\workspace\luna\Jan
@SpringBootApplication // same as
						// @Configuration+@EnableAutoConfiguration+@ComponentScan//same
						// as
						// @Configuration+@EnableAutoConfiguration+@ComponentScan
// @EnableSwagger2 //config swagger annotation
public class app {
	public static void main(String[] args) {
		SpringApplication.run(app.class, args);
	}

	/**
	 * 文件上传配置
	 * 
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 单个文件最大
		factory.setMaxFileSize("10240KB"); // KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
}