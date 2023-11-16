package com.busticket.base;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.busticket.exceptions.BusException;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;
import com.busticket.service.AdminServiceImpl;


public class AdminBase {
	AdminServiceImpl adminServiceimpl = new AdminServiceImpl();
	HomeBase homeBase=new HomeBase();
	Logger log = Logger.getLogger(AdminBase.class);
	private static AdminBase adminbaseInstance= null;
	private  AdminBase() {}
	public static AdminBase getInstance() 
	{
		if(adminbaseInstance == null) adminbaseInstance= new AdminBase();
		return adminbaseInstance;
	}
	public void adminLogin(String username,String password) 
	{
		try
		{
		if(adminServiceimpl.adminLogin(username, password)) 
		{
			homeBase.adminmethods();
		}
		else 
			log.info("Not a Admin");
		}
		catch (BusException | SQLException  e) 
		{
			log.error(e);
		}
			
	}
	
	public void addBus(Bus bus) 
	{
		try
		{
		if(adminServiceimpl.addBus(bus))
		{
			log.info("Bus Added Successfully !!!!");
			homeBase.adminmethods();
		}
		else
		{
			homeBase.adminmethods();
		}
		}
		catch (BusException | SQLException e) {
			log.info(e.getMessage());
		}
	}
	
	public void deleteBus(int busno)
	{
		try
		{
		if(adminServiceimpl.deleteBus(busno))
		{
			log.info("Bus deleted Successfully!!!!!");
			homeBase.adminmethods();
		}
		else
		{
			homeBase.adminmethods();
		}
		}
		catch (BusException | SQLException e) {
			log.info(e.getMessage());
		}
	}
	
	public void updateBus(int busno,String date)
	{
		try
		{
		if(adminServiceimpl.updateBus(busno,date))
		{
			log.info("Bus Updated Successfully!!!!!!");
			homeBase.adminmethods();
		}
		else
		{
			homeBase.adminmethods();
		}
		}
		catch (BusException | SQLException e) {
			log.info(e.getMessage());
		}
	}
	
	public void viewBookingDetails(String date)
	{
		try
		{
		List<Bus> bus=adminServiceimpl.viewAllBuses(date);
		if(bus.isEmpty())
			{log.info("No buses in this route!");
		homeBase.adminmethods();}
		else {
			bus.forEach(log::info);
			homeBase.adminmethods();
		}
		}
		catch (SQLException e) {
			e.getLocalizedMessage();
		}
	}
	
	public void updateAvaliableSeats(int busno,int avaliableseats)
	{
		try
		{
		if(adminServiceimpl.updateAvaliableSeats(busno, avaliableseats))
		{
			log.info("Bus Updated Successfully!!!!");
			homeBase.adminmethods();
		}
		else
		{
			homeBase.adminmethods();
		}
		}
		catch (BusException | SQLException e) {
			log.info(e.getMessage());
		}
	}
	
	public void getAllTickets(String date)
	{
		try
		{
		List<BookTicket> bookTickets=new ArrayList<>(adminServiceimpl.getAllTickets(date));
		if(bookTickets.isEmpty()) 
		{
			log.info("No bus tickets are avaliable!");
			homeBase.adminmethods();
		}
		else {
			bookTickets.forEach(log::info);
			homeBase.adminmethods();
		}
		}
		catch (SQLException e) {
			log.info(e.getMessage());
			}	
	}
}
