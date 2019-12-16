package com.fdmgroup.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.model.User;
import com.fdmgroup.service.UserService;

@RestController
public class UserController {

	private UserService userService;
	private IUserDao userDao;
	
	public UserController(UserService userService, IUserDao userDao) {
		super();
		this.userService = userService;
		this.userDao = userDao;
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody()
	public User adding(@RequestBody User user) {
		return this.userService.addUser(user);
	}
	
	@RequestMapping(value="/processLogin", method = RequestMethod.POST)
	public User login(@RequestParam("usernameParam") String usernameInput, 
			@RequestParam("passwordParam") String passwordInput) {
		/* User userTemp = new User(); */
		User userTemp = (User) this.userDao.getUser(usernameInput, passwordInput);

		if(userTemp!= null) {
			return userTemp;
		}

		return null;
	}
	
	@GetMapping("/getUser")
	public User findById(int id) {
		return this.userService.findById(id);
	}

}