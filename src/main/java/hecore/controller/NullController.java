package hecore.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class NullController {

	@RequestMapping("/hello")
	@ResponseBody
	public String home() {
		return "Hello";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NullController.class, args);
	}

}
