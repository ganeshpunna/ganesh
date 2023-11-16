package com.busticket.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.busticket.dao.AdminDao;
import com.busticket.exceptions.BusException;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;



public class AdminServiceImpl implements AdminService {
	
	Logger log = Logger.getLogger(AdminServiceImpl.class);
	private static AdminService adminServiceInstance= null;
	AdminDao admindao=AdminDao.getInstance();
	public static AdminService getInstance() 
	{
		if(adminServiceInstance == null)
	     adminServiceInstance= new AdminServiceImpl();
		return adminServiceInstance;
	}
	@Override
	public boolean adminLogin(String username, String password) throws BusException,SQLException 
	{
		return admindao.adminLogin(username, password);
		
	}
	
	@Override
	public boolean addBus(Bus bus) throws BusException, SQLException 
	{
		return admindao.addBus(bus);
	}
	@Override
	public boolean deleteBus(int busno) throws BusException, SQLException 
	{
		
		return admindao.deleteBus(busno);
	}
	@Override
	public boolean updateBus(int busno,String date) throws BusException, SQLException 
	{
		
		return admindao.updateBus(busno, date);
	}
	@Override
	public List<Bus> viewAllBuses(String date) throws SQLException 
	{ 
		return admindao.viewAllBuses(date).stream()
				.sorted((b1, b2) -> b1.getBname().compareTo(b2.getBname()))
				.collect(Collectors.toList());
		
	}
	@Override
	public boolean updateAvaliableSeats(int busno,int avaliableseats) throws BusException, SQLException {
		
		return admindao.updateAvaliableSeats(busno,avaliableseats);
	}
	@Override
	public List<BookTicket> getAllTickets(String date) throws SQLException {
		return admindao.getAllTickets(date);
	}

}
