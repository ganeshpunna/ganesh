package com.busticket.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.busticket.exceptions.CustomerException;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;

import com.busticket.model.Payment;

public interface UserService 
{
	public  boolean userLogin(String username,String password)throws CustomerException,SQLException;
	
	public boolean userSignUp(String username,String password,String firstname,String lastname,String mobile)throws SQLException;
	
	public List<Bus> viewBusDetails(String routefrom,String routeto,String date)throws SQLException;
	
	public boolean bookTicket(BookTicket bookticket)throws CustomerException, SQLException;
	
	public boolean deleteBusTicket(String custid)throws CustomerException, SQLException;
	
	public Map<Integer,BookTicket> bookingDetailsById(String id)throws SQLException;
	
	public boolean payment(Payment payment)throws CustomerException, SQLException;
	
	public boolean updateBoardingLocation(String bid,String boardinglocation)throws CustomerException, SQLException;

	

}
