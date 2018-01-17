
/**
 * @author Administrator
 *
 */
package hecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication //same as @Configuration+@EnableAutoConfiguration+@ComponentScan//same as @Configuration+@EnableAutoConfiguration+@ComponentScan
@EnableSwagger2		//config swagger annotation
public class app{
	public static void main(String[] args) {
		SpringApplication.run(app.class,args);
	}
}