package com.example.service;


import org.springframework.stereotype.Service;

import com.example.model.Admin;
import com.example.model.User;
@Service
public interface UserService {

	public User addComment(User u,Admin id);

	public String deleteComment(int uid);

	
}
