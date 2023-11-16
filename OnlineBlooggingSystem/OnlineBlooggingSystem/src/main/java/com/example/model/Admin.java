package com.example.model;

public class Admin {
	private int id;
	private String title;
	private String category;
	private String author;
	private String description;
	
	
	public Admin() {
		
	}

	public Admin(int id, String title, String category, String author, String description) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.author = author;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}



