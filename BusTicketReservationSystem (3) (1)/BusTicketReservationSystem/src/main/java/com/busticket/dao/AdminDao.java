package com.busticket.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.busticket.exceptions.BusException;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;
import com.busticket.util.DBUtil;
public class AdminDao {
	Logger log = Logger.getLogger(AdminDao.class);
	private static AdminDao admindaoInstance = null;

	private AdminDao() {
	}
	public static  AdminDao getInstance() 
	{
		if(admindaoInstance==null) {
			admindaoInstance= new AdminDao();
		}
		return admindaoInstance;
	}
	
	public boolean adminLogin(String username, String password) throws BusException,SQLException 
	{
		boolean isAdmin=false;
		
		String query = "SELECT * from admin where username = ? and password = ?";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			
			stmt.setString(1,username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
			{
				String uname=rs.getString(1);
				String pass=rs.getString(2);
				if(uname.equals(username) && pass.equals(password))
				{
					isAdmin=true;
					log.info("Login Successfully !!!!");
				}
				else
				{
					isAdmin=false;
					throw new BusException("Invalid  Username and Password");
				}
			}
			else
			{
				throw new BusException("Invalid Credentials");
			}
		}
			 return isAdmin;
	}
	
	public boolean addBus(Bus bus) throws BusException, SQLException
	{
		
		boolean isadmin=false;
		String query = "Insert into bus values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			
			stmt.setInt   (1,bus.getBusno());
			stmt.setString(2,bus.getDate());
			stmt.setString(3,bus.getBname());
			stmt.setString(4,bus.getRoutefrom());
			stmt.setString(5,bus.getRouteto());
			stmt.setString(6,bus.getBoardingpoint());
			stmt.setString(7,bus.getArrivingpoint());
			stmt.setString(8,bus.getBtype());
			stmt.setString(9,bus.getArrival());
			stmt.setString(10,bus.getDeparture());
			stmt.setInt   (11,bus.getTotalseats());
			stmt.setInt   (12,bus.getAvaliableseats());
			stmt.setInt	  (13,bus.getAmount());
			int rs = stmt.executeUpdate();
			if(rs==1)
			{
				log.info("***********Bus Added Successfully***********");
			}
			else
			{
				throw new BusException("**********Sorry Check your Details*********");
			}
		}
		return isadmin;
		
	}
	
	public boolean deleteBus(int busno) throws BusException, SQLException
	{
		boolean isadmin=false;
		String query = "delete from bus where busno=?";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			stmt.setInt   (1,busno);
			int rs = stmt.executeUpdate();
			if(rs==1)
			{
				log.info("***********Bus Deleted Successfully***********");
			}
			else
			{
				throw new BusException("**********Sorry Enter Correct BusNumber*********");
			}
	}
		return isadmin;
		
	}
	
	public boolean updateBus(int busno,String date) throws BusException, SQLException
	{
		boolean isAdmin=false;
		String query = "update bus set date=? where busno=?";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			
			stmt.setString(1, date);
			stmt.setInt(2, busno);
			int rs = stmt.executeUpdate();
			if(rs==1)
			{
				log.info("***********Bus Updated Successfully***********");
			}
			else
			{
				throw new BusException("**********Sorry Enter Correct BusNumber!!*********");
			}
		}
		return isAdmin;
		
	}
	
	public List<Bus> viewAllBuses(String date) throws SQLException
	{
		List<Bus> buslist = new ArrayList<>();
		String query = "select * from bus where date=?";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			stmt.setString(1, date);
			ResultSet rs = stmt.executeQuery();
			
			buslist= UserDao.commonSetter(rs);
		}
		return buslist;
	}
		
	public boolean updateAvaliableSeats(int busno,int avaliableseats) throws BusException, SQLException
	{
			boolean isAdmin=false;
			String query = "update bus set avaliableseats=? where busno=?";
			try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
			{
				
				stmt.setInt(1,avaliableseats);
				stmt.setInt(2,busno);
				int rs = stmt.executeUpdate();
				if(rs==1)
				{
					log.info("***********Bus Updated Successfully***********");
				}
				else
				{
					throw new BusException("**********Sorry Enter Correct BusNumber!!!*********");
				}
			}
			return isAdmin;
	}
			
	public List<BookTicket> getAllTickets(String date) throws SQLException 
	{
		List<BookTicket> bookticket = new ArrayList<>();
		String query = "select * from bookticket where date=?";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
			{
				stmt.setString(1, date);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) 
				{
					BookTicket book=new BookTicket();
					book.setBid(rs.getString(1));
					book.setBusnumber(rs.getInt(2));
					book.setRoutefrom(rs.getString(3));
					book.setRouteto(rs.getString(4));
					book.setDate(rs.getString(5));
					book.setName(rs.getString(6));
					book.setMobile(rs.getString(7));
					book.setTotalseats(rs.getInt(8));
					book.setBoardinglocation(rs.getString(9));
					book.setArrivinglocation(rs.getString(10));
					book.setAmount(rs.getInt(11));
					bookticket.add(book);
				}	
			}
				return bookticket;
			}
	}

