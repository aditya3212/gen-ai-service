package com.example.genaiservice.apis.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity) {

		userService.registerUser(userEntity);
		return ResponseEntity.ok("User created successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> signIn(@RequestBody UserEntity userEntity) {
		return ResponseEntity.ok(userService.signIn(userEntity));
	}

	@GetMapping("/list")
	public ResponseEntity<List<UserEntity>> getUser() {

		return ResponseEntity.ok(userService.getUserList());
	}

	@GetMapping("/hello")
	public String sayHii() {
		return "hii";
	}

}
