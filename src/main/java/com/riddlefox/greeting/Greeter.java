package com.riddlefox.greeting;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@RestController
@SpringBootApplication
public class Greeter extends SpringBootServletInitializer {

	@RequestMapping("/")
    public static String greet(String name) {
    	if(name==null) {
    		return "Hello World";
    	}
        return "Hello " + name;
    }
    
	public static void main(String[] args) throws Exception {
        SpringApplication.run(Greeter.class, args);
    }
}
