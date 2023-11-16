package com.busticket.base;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.busticket.exceptions.CustomerException;
import com.busticket.main.AppContext;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;
import com.busticket.model.Payment;
import com.busticket.service.UserServiceImpl;

public class UserBase {
	AppContext busTicketMain = new AppContext();
	static Scanner sc=new Scanner(System.in); 
	UserServiceImpl userServiceimpl=new UserServiceImpl();
	HomeBase homeBase=new HomeBase();
	Logger log = Logger.getLogger(UserBase.class);
	private static UserBase userbaseInstance= null;
	private  UserBase() {
	}
	public static UserBase getInstance() 
	{
		if(userbaseInstance == null)
			userbaseInstance= new UserBase();
		return userbaseInstance;
	}
	
	public void userLogin(String username,String password) 
	{
		try {
		if(userServiceimpl.userLogin(username,password)) 
		{
			homeBase.usermethods();
		}
		else 
			log.info("Check your username or password");
	}
		catch (CustomerException | SQLException e) {
			log.error(e);
		}
		}
	
		
	
	public void userSignUp(String username,String password,String firstname,String lastname,String mobile)
	{
		try
		{
		boolean b=userServiceimpl.userSignUp(username,password,firstname,lastname,mobile);
		if(b) {
			busTicketMain.main();
		}
		busTicketMain.main();
	}
		catch (SQLException e) {
			log.error(e);
		}
	}
	
	public void viewbusdetails(String routefrom,String routeto,String date)
	{
		try
		{
		List<Bus> bus=userServiceimpl.viewBusDetails(routefrom, routeto, date);
		if(bus.isEmpty()) 
		{
		log.info("No buses in this route!");
		homeBase.usermethods();
		}
		else {
			for(Bus b: bus)
			{
				if(b.getAvaliableseats()>0)
				{
					bus.forEach(log::info);
				}
				else
				{
					log.info("No Bus Found");
				}
			}
			log.info("Do you want to book ticket");
			log.info("1.YES");
			log.info("2.NO");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				log.info("********Book Your Ticket***********");
				homeBase.bookticket();
				break;
			case 2:
				homeBase.usermethods();
				break;
			default:
				log.info("Invalid");
				break;
			}
		}
		}
		catch (SQLException e) {
			e.getLocalizedMessage();
		}
	}
	
	public void deleteBusTicket(String custid)
	{
		try
		{
		if(userServiceimpl.deleteBusTicket(custid))
		{
			log.info("BusTicket deleted Successfully!!!!!");
			homeBase.usermethods();
		}
		else
		{
			homeBase.usermethods();
		}
		}
		catch (CustomerException | SQLException e) {
			log.info(e.getMessage());
		}
	}
	
	public void bookTicket(BookTicket bookticket)
	{
		try
		{
		if(userServiceimpl.bookTicket(bookticket))
		{
			log.info("Bus Selected Successfully !!!!");
			log.info("Do the Payment");
			homeBase.payment();
		}
		else
		{
			homeBase.payment();
		}
		}
		catch (CustomerException | SQLException e) {
			log.info(e.getMessage());
		}
		
	}

	public void viewBookingDetails(String id)
	{
		try
		{
		Map<Integer, BookTicket> book=userServiceimpl.bookingDetailsById(id);
		if(book.isEmpty()) 
		{
			log.info("No Ticket in found!");
			homeBase.usermethods();
		}
			
		else {
			log.info(book);
			homeBase.usermethods();
		}
		}
		catch (SQLException e) {
			e.getLocalizedMessage();
		}
	}
	
	public void payment(Payment pay)
	{
		try
		{
		if(userServiceimpl.payment(pay))
		{
			log.info("Payment Done Successfully !!!!");
			log.info("Bus Ticket Booked Successfully");
			homeBase.usermethods();
		}
		else
		{
			homeBase.usermethods();
		}
		}
		catch (CustomerException | SQLException e) {
			log.info(e.getMessage());
		}
	}
	
	public void updateBoardingLocation(String bid,String boardinglocation)
	{
		try
		{
		if(userServiceimpl.updateBoardingLocation(bid, boardinglocation))
		{
			log.info("Boardinglocation Updated Successfully!!!!!!");
			homeBase.usermethods();
		}
		else
		{
			homeBase.usermethods();
		}
		}
		catch (CustomerException | SQLException e) {
			log.info(e.getMessage());
		}
	}
	
}
