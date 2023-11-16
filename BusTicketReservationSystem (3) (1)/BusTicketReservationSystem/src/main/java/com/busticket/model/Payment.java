package com.busticket.model;


public class Payment {
	
	private String cardholdername;
	private String cardnumber;
	private int cvv;
	private String expirydate;
	private String transactionid;
	
	public String getCardholdername() {
		return cardholdername;
	}
	public void setCardholdername(String cardholdername) {
		this.cardholdername = cardholdername;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	@Override
	public String toString() {
		return "Payment [cardholdername=" + cardholdername + ", cardnumber=" + cardnumber + ", cvv=" + cvv
				+ ", expirydate=" + expirydate + ", transactionid=" + transactionid + "]";
	}
	public Payment(String cardholdername, String cardnumber, int cvv, String expirydate, String transactionid) {
		super();
		this.cardholdername = cardholdername;
		this.cardnumber = cardnumber;
		this.cvv = cvv;
		this.expirydate = expirydate;
		this.transactionid = transactionid;
	}
	public Payment() {
		
	}
	
	
	
	
	
	

}
