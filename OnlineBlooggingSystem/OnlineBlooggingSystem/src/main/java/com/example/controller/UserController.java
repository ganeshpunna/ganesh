package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Admin;
import com.example.model.User;
import com.example.service.AdminService;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userservice;
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/add/{id}")
	  public ResponseEntity<User> addComment(@RequestBody User user,@PathVariable int id) 
	  {
			Admin admin = adminService.getAdminById(id);
		  return new ResponseEntity<User>(userservice.addComment(user,admin),HttpStatus.ACCEPTED);
	  }
	
	@DeleteMapping("/delete/{id}")
	  public String deleteComment(@PathVariable int id) {
	      return userservice.deleteComment(id);
	  }
	

}
