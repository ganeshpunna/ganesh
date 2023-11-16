package com.example.controller;

import java.util.List;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Admin;
import com.example.model.User;
import com.example.service.AdminService;
import com.example.service.AdminServiceImpl;
import com.example.service.UserService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminservice;
	private UserService userservice; 
	
	@PostMapping("/add")
  public Admin addPost(@RequestBody Admin admin) {
      return adminservice.addPost(admin);
  }
	
	@GetMapping("/findall")
  public List<Admin> findAll() {
      return this.adminservice.getAllPosts();
  }
	
	@DeleteMapping("/delete/{id}")
  public String deletePost(@PathVariable int id) {
      return adminservice.deletePost(id);
  }
    @GetMapping("/findbyid/{id}")
  public Admin findProductById(@PathVariable int id) {
        return adminservice.getPostById(id);
    }
	@PutMapping("/update")
  public Admin updatePost(@RequestBody Admin p) {
        return adminservice.updatePost(p);
    }
	
	@GetMapping("/category/{category}")
  public List<Admin> getPostsByCategory(@PathVariable("category") String cat) {
		return adminservice.getPostsByCategory(cat);
	}
	
	@GetMapping("/title/{title}")
	public List<Admin> getPostsByTitle(@PathVariable("title") String cat)
	{
		return adminservice.getPostsByTitle(cat);
	}
	
	@GetMapping("/comment/{id}")
	public String getCommentsById(@PathVariable int id)
	{
		Admin admin=adminservice.getAdminById(id);
		String msg=adminservice.getCommentsById(admin);
		return msg;
	}
	
	
}
