package com.example.genaiservice.user;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/hello")
	String sayHello() {
		return "Hello";
	}
	
	@GetMapping("/login")
	ResponseEntity<String> login(){
		
		
		return new ResponseEntity<String>("HII",HttpStatusCode.valueOf(200));
	}
}
