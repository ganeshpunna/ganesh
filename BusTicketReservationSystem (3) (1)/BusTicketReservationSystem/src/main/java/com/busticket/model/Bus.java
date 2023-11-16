package com.busticket.model;

public class Bus {
	
	private int busno;
	private String date;
	private String bname;
	private String routefrom;
	private String routeto;
	private String boardingpoint;
	private String arrivingpoint;
	private String btype;
	private String arrival;
	private String departure;
	private int totalseats;
	private int avaliableseats;
	private int amount;
	

	public int getBusno() {
		return busno;
	}

	public void setBusno(int busno) {
		this.busno = busno;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
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

	public String getBoardingpoint() {
		return boardingpoint;
	}

	public void setBoardingpoint(String boardingpoint) {
		this.boardingpoint = boardingpoint;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public int getTotalseats() {
		return totalseats;
	}

	public void setTotalseats(int totalseats) {
		this.totalseats = totalseats;
	}

	public int getAvaliableseats() {
		return avaliableseats;
	}

	public void setAvaliableseats(int avaliableseats) {
		this.avaliableseats = avaliableseats;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	

	public String getArrivingpoint() {
		return arrivingpoint;
	}

	public void setArrivingpoint(String arrivingpoint) {
		this.arrivingpoint = arrivingpoint;
	}
	
	

	@Override
	public String toString() {
		return "Bus [busno=" + busno + "\ndate=" + date + "\nbname=" + bname + "\nroutefrom=" + routefrom + "\nrouteto="
				+ routeto + "\nboardingpoint=" + boardingpoint + "\narrivingpoint=" + arrivingpoint + "\nbtype=" + btype
				+ "\narrival=" + arrival + "\ndeparture=" + departure + "\ntotalseats=" + totalseats
				+ "\nAvaliableseats=" + avaliableseats + "\namount=" + amount + "]";
	}

	
	
	
	
	
	

}
