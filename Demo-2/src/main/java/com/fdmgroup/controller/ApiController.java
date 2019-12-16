package com.fdmgroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.model.User;
import com.fdmgroup.service.UserService;

@RestController
@RequestMapping("/users")
public class ApiController {
	
	private UserService userService;
	private IUserDao userDao;
	
	@Autowired
	public ApiController(UserService userService, IUserDao userDao) {
		super();
		this.userService = userService;
		this.userDao = userDao;
	}
	
	@GetMapping("/all")
	public List<User> showAllUsers(){
		return this.userDao.findAll();
	}
}
