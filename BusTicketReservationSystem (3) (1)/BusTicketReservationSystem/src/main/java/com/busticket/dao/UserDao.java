package com.busticket.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.busticket.base.HomeBase;
import com.busticket.exceptions.CustomerException;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;
import com.busticket.model.Payment;
import com.busticket.util.DBUtil;

public class UserDao {
	Logger log = Logger.getLogger(UserDao.class);
	private static UserDao userdaoInstance = null;

	private UserDao() {

	}
	public static UserDao getInstance() 
	{
		if(userdaoInstance==null) userdaoInstance= new UserDao();
		return userdaoInstance;
	}
	public boolean userLogin(String username,String password) throws CustomerException,SQLException 
	{
		boolean isUser=false;
		String query = "SELECT * from user where username = ? and password = ?";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			
			stmt.setString(1,username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
			{
				String useruname=rs.getString(2);
				String userpass=rs.getString(3);
				if(useruname.equals(username) && userpass.equals(password))
				{
					isUser=true;
					log.info("Login Successfully !!!!");
				}
				else
				{
					isUser=false;
					throw new CustomerException("Invalid  Username and Password");
				}
			}
			else
			{
				throw new CustomerException("Invalid Credentials");
			}
		}
		return isUser;
	}
	
	int getValue;
	public int generateId(String autoId)
	{
		
		try(Statement stmt= DBUtil.getConnection().createStatement();)
		{
			ResultSet rs=stmt.executeQuery(autoId);
			if(rs.next())
			{
				return Integer.parseInt(rs.getString(1));
			}
			
		} catch (SQLException e) {
			log.info(e);
		}
		return 1;
	}
	
	public boolean userSignUp(String username,String password,String firstname,String lastname,String mobile) throws SQLException 
	{
		boolean isAdmin=false;
		getValue=generateId("select count(custid)+1 from user");
		String query = "insert into user values(?,?,?,?,?,?)";
		String id="CUST0000"+getValue;
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			stmt.setString(1,id);
			stmt.setString(2,username);
			stmt.setString(3,password);
			stmt.setString(4,firstname);
			stmt.setString(5,lastname);
			stmt.setString(6,mobile);
			int rs = stmt.executeUpdate();
			if(rs==1)
			{
				log.info("***********Welcome User  *********** "+username);
				log.info("Please Login");
			}
			else
			{
				log.info("**********Check your Details*********");
			}
		}
			return isAdmin;
	}
	
	public List<Bus> viewBusDetails(String routefrom,String routeto,String date) throws SQLException
	{
		List<Bus> buslist = new ArrayList<>();
		String query = "select * from bus where routefrom=? and routeto=? and date=?";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			stmt.setString(1,routefrom);
			stmt.setString(2,routeto);
			stmt.setString(3,date);
			ResultSet rs = stmt.executeQuery();
			buslist= commonSetter(rs);
		}
		return buslist;
	}
	
	int getValue1;
	public int bookingId(String id)
	{
		try(Statement stmt= DBUtil.getConnection().createStatement();)
		{
			ResultSet rs=stmt.executeQuery(id);
			if(rs.next())
			{
				return Integer.parseInt(rs.getString(1));
			}
			
		} catch (SQLException e) {
			log.info(e);
		}
		return 1;
	}
	
	public boolean bookTicket(BookTicket bookticket) throws CustomerException, SQLException
	{
		boolean isuser=false;
		Bus bus = searchByBusId(bookticket.getBusnumber());
		int amount=bookticket.getTotalseats() * bus.getAmount();
		getValue1=bookingId("select count(bid)+1 from bookticket");
		String query = "Insert into bookticket values(?,?,?,?,?,?,?,?,?,?,?)";
		String id="ID0000"+getValue1;
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			stmt.setString(1,id);
			stmt.setInt   (2,bookticket.getBusnumber());
			stmt.setString(3,bookticket.getRoutefrom());
			stmt.setString(4,bookticket.getRouteto());
			stmt.setString(5,bookticket.getDate());
			stmt.setString(6,bookticket.getName());
			stmt.setString(7,bookticket.getMobile());
			stmt.setInt   (8,bookticket.getTotalseats());
			stmt.setString(9,bookticket.getBoardinglocation());
			stmt.setString(10,bookticket.getArrivinglocation());
			stmt.setInt	  (11,amount);
			int rs = stmt.executeUpdate();
			if(rs==1)
			{
				log.info("Total Amount to pay "+amount);
				log.info("***********BusTicket Selected Successfully!!!!!***********");
			}
			else
			{
				throw new CustomerException("**********Sorry Server Issue*********");
			}
		}
		return isuser;
		
	}
	
	private Bus searchByBusId(int busnumber) throws SQLException 
	{	
		String query = "select * from bus where busno=?";
		Bus bus = new Bus();
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			
			stmt.setInt(1, busnumber);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				bus.setBname(rs.getString("bname"));
				bus.setAmount(rs.getInt("amount"));
				bus.setBtype(rs.getString("btype"));
				bus.setBusno(rs.getInt("busno"));
				bus.setDate(rs.getString("date"));
				bus.setAvaliableseats(rs.getInt("avaliableseats"));
			}
		}
		return bus;
	}
	
	public boolean deleteBusTicket(String custid) throws CustomerException, SQLException
	{
		boolean isadmin=false;
		String query = "delete from bookticket where bid=?";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			stmt.setString(1,custid);
			int rs = stmt.executeUpdate();
			if(rs==1)
			{
				log.info("***********BusTickets Deleted Successfully***********");
			}
			else
			{
				throw new CustomerException("**********Sorry Enter Correct BusId*********");
			}
	}
		return isadmin;
	}
	
	int getValue2;
	public int transactionId(String trans)
	{
		try(Statement stmt= DBUtil.getConnection().createStatement();)
		{
			ResultSet rs=stmt.executeQuery(trans);
			if(rs.next())
			{
				return Integer.parseInt(rs.getString(1));
			}
			
		} catch (SQLException e) {
			log.info(e);
		}
		return 1;
	}
	
	
	public boolean payment(Payment pay) throws CustomerException, SQLException
	{
		boolean isUser=false;
		getValue2=transactionId("select count(transactionid)+1 from payment");
		getValue1=bookingId("select count(bid) from bookticket");
		String query = "insert into payment values(?,?,?,?,?)";
		String transactionid="8790438398"+getValue2;
		String bookingid="ID0000"+getValue1;
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			stmt.setString(1,pay.getCardholdername());
			stmt.setString (2,pay.getCardnumber());
			stmt.setInt   (3,pay.getCvv());
			stmt.setString(4,pay.getExpirydate());
			stmt.setString(5,transactionid);
			int rs = stmt.executeUpdate();
			if(rs==1)
			{
				log.info("***********Payment is Done Successfully***********");
				log.info("Your Transaction id is :"+transactionid);
				log.info(" ");
				log.info("-------Bus Ticket Booked Successfully-------");
				log.info("Your booking ID is:"+bookingid);
				reduceSeats();
			}
			else
			{
				throw new CustomerException("**********Check your Card Details*********");
			}
		}
			return isUser;
	}
	
	public boolean updateBoardingLocation(String bid,String boardinglocation) throws CustomerException, SQLException
	{
			boolean isAdmin=false;
			String query = "update bookticket set boardinglocation=? where bid=?";
			try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
			{
				
				stmt.setString(1,boardinglocation);
				stmt.setString(2,bid);
				int rs = stmt.executeUpdate();
				if(rs==1)
				{
					log.info("***********BoardingLocation Updated Successfully***********");
				}
				else
				{
					throw new CustomerException("**********Sorry Enter Correct BusId!!!*********");
				}
			}
			return isAdmin;
	}
	
	public Map<Integer, BookTicket> viewBookDetailsById(String id) throws SQLException{
		Map<Integer, BookTicket> booklist = new HashMap<>();
		String query = "select * from bookticket where bid=?";
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			stmt.setString(1,id);
			ResultSet rs1 = stmt.executeQuery();
			int a = 1;
			while (rs1.next()) {
				BookTicket book=new BookTicket();
				book.setBid(rs1.getString(1));
				book.setBusnumber(rs1.getInt(2));
				book.setRoutefrom(rs1.getString(3));
				book.setRouteto(rs1.getString(4));
				book.setDate(rs1.getString(5));
				book.setName(rs1.getString(6));
				book.setMobile(rs1.getString(7));
				book.setTotalseats(rs1.getInt(8));
				book.setBoardinglocation(rs1.getString(9));
				book.setArrivinglocation(rs1.getString(10));
				book.setAmount(rs1.getInt(11));
				booklist.put(a++, book);
			}
			return booklist;
		}
	}
	public void reduceSeats() throws SQLException {
		String query = "update bus set avaliableseats=? where busno=?";
		int avaliableseats=0;
		Bus bus;
		try(PreparedStatement stmt= DBUtil.getConnection().prepareStatement(query);)
		{
			bus = searchByBusId(HomeBase.booktic.getBusnumber());
			if(bus.getAvaliableseats()>0)
			{
				avaliableseats=bus.getAvaliableseats()-HomeBase.booktic.getTotalseats();
				stmt.setInt(1,avaliableseats);
				stmt.setInt(2,bus.getBusno());
				stmt.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.getLocalizedMessage();
		}
	}
	
	public static List<Bus> commonSetter(ResultSet rs) throws SQLException
	{
		List<Bus> buslist = new ArrayList<>();
		
		while (rs.next()) 
		{
			Bus bus = new Bus();
			bus.setBusno(rs.getInt(1));
			bus.setDate(rs.getString(2));
			bus.setBname(rs.getString(3));
			bus.setRoutefrom(rs.getString(4));
			bus.setRouteto(rs.getString(5));
			bus.setBoardingpoint(rs.getString(6));
			bus.setArrivingpoint(rs.getString(7));
			bus.setBtype(rs.getString(8));
			bus.setArrival(rs.getString(9));
			bus.setDeparture(rs.getString(10));
			bus.setTotalseats(rs.getInt(11));
			bus.setAvaliableseats(rs.getInt(12));
			bus.setAmount(rs.getInt(13));
			buslist.add(bus);
		}
		return buslist;
	}
	
}
