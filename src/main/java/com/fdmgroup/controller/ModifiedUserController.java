package com.fdmgroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.model.User;
import com.fdmgroup.service.UserService;

@RestController
public class ModifiedUserController {
	
	private UserService userService;
	
	@Autowired
	public ModifiedUserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("api/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
		
	}

	@GetMapping("api/user/{user-id}")
	public ResponseEntity<User> getById(@PathVariable(name = "user-id", required=true)int id){
		User user = userService.findById(id);
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("api/addUser")
	public ResponseEntity<User> register(@RequestBody User user){
		return ResponseEntity.ok(this.userService.addUser(user));
	}
}
