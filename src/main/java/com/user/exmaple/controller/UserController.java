package com.user.exmaple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.exmaple.dto.UserRequest;
import com.user.exmaple.dto.UserResponse;
import com.user.exmaple.model.User;
import com.user.exmaple.service.UserService;
import com.user.exmaple.util.JwtUtil;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer saveTheUser = service.saveTheUser(user);
		String body = "user '" + saveTheUser + "' saved...!";
		return ResponseEntity.ok(body);
	}

	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request) {
		String token = jwtUtil.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token, "successfully generated..!"));
	}
}
