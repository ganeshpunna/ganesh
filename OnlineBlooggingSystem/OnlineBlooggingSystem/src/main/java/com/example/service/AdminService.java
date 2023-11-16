 package com.example.service;

import java.util.List;

import com.example.model.Admin;
import com.example.model.User;

public interface AdminService {
	public Admin addPost(Admin p);
	public Admin getAdminById(int id);
	public String deletePost(int pid);
	public Admin updatePost(Admin p);
	public List<Admin> getAllPosts();
	public Admin getPostById(int pid);
	public List<Admin> getPostsByCategory(String s);
	public List<Admin> getPostsByTitle(String s);
	public String getCommentsById(Admin admin);

}
