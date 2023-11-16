package com.example.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.model.Admin;
import com.example.model.User;
@Service
public class UserServiceImpl implements UserService {
	
	
	static List<User> ulist = new ArrayList<>();
	@Override
	public User addComment(User u,Admin a) {
	
		User us=new User();
		for(Admin list:AdminServiceImpl.list) {
			if(list.getId()==a.getId())
			{
				us.setComment(u.getComment());
				ulist.add(us);
				return u;
			}
		}
			return null;
	}

	@Override
	public String deleteComment(int uid) {
		ulist.removeIf(x -> x.getId() == (uid));
		return "Deleted Successfully";
	}


	

}
