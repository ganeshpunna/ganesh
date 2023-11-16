package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Admin;
import com.example.model.User;

@Service
public class AdminServiceImpl implements AdminService {
	static List<Admin> list = new ArrayList<>();
	
	@Override
	public Admin addPost(Admin p) {
		
		Admin admin = new Admin();
		admin.setId(p.getId());
		admin.setTitle(p.getTitle());
		admin.setCategory(p.getCategory());
		admin.setAuthor(p.getAuthor());
		admin.setDescription(p.getDescription());
		list.add(admin);
		return admin;
	
		/*
		 list = List.of(
	                new Admin(1,"Wedding","Photo","Ganesh","Sister's marriage"),
	                new Admin(2,"Reel","Video","Rakesh","Rain dance"),
	                new Admin(3,"Reel","Video","Sreekar","DumbSmash")
	        );
		return p;
		*/
	}

	@Override
	public String deletePost(int pid) {
		list.removeIf(x -> x.getId() == (pid));
		return "Removed Successfully";
	}

	@Override
	public Admin updatePost(Admin p) {
		int idx = 0;
        int id = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == (p.getId())) {
                id = p.getId();
                idx = i;
                break;
            }
        }
        Admin admin = new Admin();
		admin.setId(p.getId());
		admin.setTitle(p.getTitle());
		admin.setCategory(p.getCategory());
		admin.setAuthor(p.getAuthor());
		admin.setDescription(p.getDescription());
		list.add(admin);
		return admin;
		
	}

	@Override
	public List<Admin> getAllPosts() {
		return list;
	}

	@Override
	public List<Admin> getPostsByCategory(String s) {
		List<Admin> l=new ArrayList<>();
		for(Admin p:list)
		{
			if(p.getCategory().equalsIgnoreCase(s))
			{
				l.add(p);
			}
		}
		return l ;
	}

	@Override
	public List<Admin> getPostsByTitle(String s) {
		List<Admin> l=new ArrayList<>();
		for(Admin p:list)
		{
			if(p.getTitle().equalsIgnoreCase(s))                                                               //The equalsIgnoreCase() method compares two strings, ignoring lower case and upper case differences.
			{
				l.add(p);
			}
		}
		return l;
	}

	@Override
	public Admin getPostById(int pid) {
		for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == (pid)) {
                return list.get(i);
            }
        }
		return null;
	}

	@Override
	public Admin getAdminById(int id) {
		for(Admin i:list) {
			if(i.getId()==id) {
				
				return i;
			}
		}
		return null;
	}

	public String getCommentsById(Admin id) {
		for(User ulist:UserServiceImpl.ulist) {
			for(Admin i:list) {
			if(i.getId()==id.getId())
			{
				return ulist.getComment();
				
			}
		}
	}
		return "Invalid Id";
	

	}

	
}
