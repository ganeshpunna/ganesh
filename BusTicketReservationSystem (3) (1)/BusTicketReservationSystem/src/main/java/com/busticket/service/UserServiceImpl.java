package com.busticket.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.busticket.dao.UserDao;
import com.busticket.exceptions.CustomerException;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;
import com.busticket.model.Payment;

public class UserServiceImpl implements UserService
{
	Logger log = Logger.getLogger(UserServiceImpl.class);
	private static UserService userServiceInstance= null;
	UserDao userdao=UserDao.getInstance();
	
	public static  UserService getInstance() 
	{
		if(userServiceInstance == null) userServiceInstance= new UserServiceImpl();
		return userServiceInstance;
	}

	@Override
	public boolean userLogin(String username, String password) throws CustomerException,SQLException
	{
		return userdao.userLogin(username,password);
	}
	@Override
	public boolean userSignUp(String username,String password,String firstname,String lastname,String mobile) throws SQLException 
	{
		return userdao.userSignUp(username,password,firstname,lastname,mobile);
	}
	@Override
	public List<Bus> viewBusDetails(String routefrom, String routeto,String date) throws SQLException {
		return userdao.viewBusDetails(routefrom, routeto, date);
	}
	@Override
	public boolean deleteBusTicket(String custid) throws CustomerException, SQLException {
		return userdao.deleteBusTicket(custid);
	}
	@Override
	public boolean bookTicket(BookTicket bookticket) throws CustomerException, SQLException {
		return userdao.bookTicket(bookticket);
	}
	@Override
	public boolean payment(Payment payment) throws CustomerException, SQLException 
	{
		return userdao.payment(payment);
	}
	@Override
	public boolean updateBoardingLocation(String bid,String boardinglocation) throws CustomerException, SQLException 
	{
		return userdao.updateBoardingLocation(bid,boardinglocation);
	}
	@Override
	public Map<Integer, BookTicket> bookingDetailsById(String id) throws SQLException {
		
		return  userdao.viewBookDetailsById(id);
	}
	public void reduceSeats() throws SQLException {
		userdao.reduceSeats();
		
	}
	
	
	
	

}
