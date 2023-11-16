package com.busticket.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BookTicket {
	
	@Id
	private String bid;
	private int busnumber;
	private String routefrom;
	private String routeto;
	private String date;
	private String name;
	private String mobile;
	private int totalseats;
	private String boardinglocation;
	private String arrivinglocation;
	private int amount;
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getBusnumber() {
		return busnumber;
	}
	public void setBusnumber(int busnumber) {
		this.busnumber = busnumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getTotalseats() {
		return totalseats;
	}
	public void setTotalseats(int totalseats) {
		this.totalseats = totalseats;
	}
	public String getRoutefrom() {
		return routefrom;
	}
	public void setRoutefrom(String routefrom) {
		this.routefrom = routefrom;
	}
	public String getRouteto() {
		return routeto;
	}
	public void setRouteto(String routeto) {
		this.routeto = routeto;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getBoardinglocation() {
		return boardinglocation;
	}
	public void setBoardinglocation(String boardinglocation) {
		this.boardinglocation = boardinglocation;
	}
	public String getArrivinglocation() {
		return arrivinglocation;
	}
	public void setArrivinglocation(String arrivinglocation) {
		this.arrivinglocation = arrivinglocation;
	}
	
	
	
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	
	public BookTicket() {
		super();
	}
	public BookTicket(String bid, int busnumber, String routefrom, String routeto, String date, String name,
			String mobile, int totalseats, String boardinglocation, String arrivinglocation, int amount) {
		super();
		this.bid = bid;
		this.busnumber = busnumber;
		this.routefrom = routefrom;
		this.routeto = routeto;
		this.date = date;
		this.name = name;
		this.mobile = mobile;
		this.totalseats = totalseats;
		this.boardinglocation = boardinglocation;
		this.arrivinglocation = arrivinglocation;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "BookTicket [bid=" + bid + ", busnumber=" + busnumber + ", routefrom=" + routefrom + ", routeto="
				+ routeto + ", date=" + date + ", name=" + name + ", mobile=" + mobile + ", totalseats=" + totalseats
				+ ", boardinglocation=" + boardinglocation + ", arrivinglocation=" + arrivinglocation + ", amount="
				+ amount + "]";
	}
	
}
