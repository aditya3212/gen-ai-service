package com.example.genaiservice.apis.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
	
	private final UserService userService;
	
	
	@GetMapping("/list")
	public ResponseEntity<List<UserEntity>> getUser(){
		
		return ResponseEntity.ok(userService.getUserList());
	}
	
	@GetMapping("/hello")
	public String sayHii() {
		return "hii";
	}

}
