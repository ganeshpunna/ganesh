package com.busticket.service;

import java.sql.SQLException;
import java.util.List;

import com.busticket.exceptions.BusException;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;

public interface AdminService {
	
	public  boolean adminLogin(String username,String password)throws BusException,SQLException ;
	
	public boolean addBus(Bus bus)throws BusException, SQLException ;
	
	public boolean deleteBus(int busno)throws BusException, SQLException ;
	
	public boolean updateBus(int busno,String date)throws BusException, SQLException ;
	
	public List<Bus> viewAllBuses(String date)throws SQLException ;
	
	public boolean updateAvaliableSeats(int busno,int avaliableseats)throws BusException, SQLException ;
	
	public List<BookTicket> getAllTickets(String date)throws SQLException ;
	

	
	
	

}
