package com.example.model;

public class User {
	private int id;
	private String comment;
	private String like;
	
	public User() {
		
	}
	public User(int id, String comment, String like) {
		super();
		this.id = id;
		this.comment = comment;
		this.like = like;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", comment=" + comment + ", like=" + like + "]";
	}
	
	

}
