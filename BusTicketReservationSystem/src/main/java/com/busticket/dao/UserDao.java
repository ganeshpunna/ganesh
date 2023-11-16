package com.busticket.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.busticket.base.HomeBase;
import com.busticket.exceptions.CustomerException;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;
import com.busticket.model.Customer;
import com.busticket.model.Payment;
import com.busticket.util.HibernateUtil;

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
		public  String custoomerLoginId = "";

	public boolean userLogin(String username, String password) throws CustomerException {
	    boolean isUser = false;
	    try
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        String hql = "FROM Customer A WHERE A.username = :username AND A.password = :password";
	        Query<Customer> query = session.createQuery(hql, Customer.class);
	        query.setParameter("username", username);
	        query.setParameter("password", password);
	        Customer user = query.uniqueResult();
	        session.getTransaction().commit();
	        if (user != null) 
	        {
	            isUser = true;
	            custoomerLoginId = user.getCustid();
	            log.info("Login Successfully !!!!");
	        } 
	        else 
	        {
	            throw new CustomerException("Invalid Username and Password");
	        }
	        session.close();
	    } 
	    catch (Exception e) 
	    {
	        throw new CustomerException("Invalid Credentials");
	    }
	    return isUser;
	}
	int getValue;
	public int generateId()
	{
		 try (Session session = HibernateUtil.getSessionFactory().openSession();)
		 {
	     session.beginTransaction();
	     Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
	     List<Customer> customers = query.list();
	     session.getTransaction().commit();
 	     return customers.size()+1;
		 }
		 catch (Exception e) 
		 {
			log.info(e);
		 }
		 return 1;
	}
	
	public boolean userSignUp(String username,String password,String firstname,String lastname,String mobile) 
	{
		String id="CUST0000"+generateId();
		 try
		 {
			 SessionFactory factory = HibernateUtil.getSessionFactory();
		     Session session = factory.openSession();
			 session.beginTransaction();
		     Customer customer = new Customer();
		     customer.setCustid(id); 
		     customer.setUsername(username);
		     customer.setPassword(password);
		     customer.setFirstname(firstname);
		     customer.setLastname(lastname);
		     customer.setMobile(mobile);
		     session.persist(customer);
		     session.getTransaction().commit();
		     log.info("***********Welcome User  *********** " + username);
		     log.info("Please Login");
		     session.close();
		     return true;
		 } 
		 catch (Exception e) 
		 {
			 log.error("Error signing up user", e);
		     return false;
		 }
	}
	
	public List<Bus> viewBusDetails(String routefrom, String routeto, String date) {
	    List<Bus> busList = new ArrayList<>();
	    try
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query<Bus> query = session.createQuery("FROM Bus WHERE routefrom = :from AND routeto = :to AND date = :busDate", Bus.class);
	        query.setParameter("from", routefrom);
	        query.setParameter("to", routeto);
	        query.setParameter("busDate", date);
	        busList = query.list();
	        session.getTransaction().commit();
	        session.close();
	    } 
	    catch (Exception e) 
	    {
	    	log.info(e.getMessage());
	    }
	    return busList;
	}

	int getValue1;
	public int bookingId( )
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession();)
		{
		     session.beginTransaction();
		     Query<BookTicket> query = session.createQuery("FROM BookTicket ", BookTicket.class);
		     List<BookTicket> bookTicket = query.list(); 
		     session.getTransaction().commit();
	 	     return bookTicket.size()+1;			
		} 
		catch (Exception e) 
		{
			log.info(e);
		}
		return 1;
	}
	
	public boolean bookTicket(BookTicket bookticket ) throws CustomerException {
		boolean isuser=false;
 	    try
 	    {
 	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Bus bus = searchByBusId(bookticket.getBusnumber());
			int amount=bookticket.getTotalseats() * bus.getAmount();
	        String id = "ID0000" + bookingId();
	        bookticket.setBid(id);
	        bookticket.setAmount(amount);
	        String hql = "FROM Customer c WHERE c.custid = :userId";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("userId", custoomerLoginId);
            Customer customer = query.uniqueResult();
            bookticket.setCustomer(customer);
	        session.persist(bookticket);
	        session.getTransaction().commit();
	        log.info("Total Amount to pay " + amount);
	        log.info("***********BusTicket Selected Successfully!!!!!***********");
	        session.close();
	        isuser = true;
	    } 
 	    catch (Exception e) 
 	    {
	        log.error("Error inserting bookticket", e);
	        throw new CustomerException("**********Sorry Server Issue*********");
	    }
 	    return isuser;
 	}

	public Bus searchByBusId(int busnumber) {
	    Bus bus = null;
	    try
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query<Bus> query = session.createQuery("FROM Bus WHERE busno = :busNumber", Bus.class);
	        query.setParameter("busNumber", busnumber);
	        bus = query.uniqueResult();
	        session.getTransaction().commit();
	        session.close();
	    } 
	    catch (Exception e) 
	    {
	    	log.info("**"+e.getMessage());
	    }
	    return bus;
	}

	public boolean deleteBusTicket(String cusid) throws CustomerException {
		try(SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession())
	    {
	        session.beginTransaction();
	        Query query = session.createQuery("DELETE FROM BookTicket WHERE bid = :cusId");
	        query.setParameter("cusId", cusid);
	        int rowsDeleted = query.executeUpdate();
	        session.getTransaction().commit();
	        if (rowsDeleted == 1) 
	        {
	            log.info("***********BusTickets Deleted Successfully***********");
	        } 
	        else 
	        {
	            throw new CustomerException("**********Sorry Enter Correct BusId*********");
	        }
	    } 
		catch (Exception e) 
		{
	        throw new CustomerException("Error deleting bus ticket");
	    }
	    return true; 
	}
	
	int getValue2;
	public int transactionId()
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession();)
		{
			session.beginTransaction();
	        Query<Payment> query = session.createQuery("FROM Payment ", Payment.class);
	        List<Payment> payment = query.list();	        
	        session.getTransaction().commit();
 	        return payment.size()+1;			
		} 
		catch (Exception e) 
		{
			log.info(e);
		}
		return 1;
	}
	
	public boolean payment(Payment pay) throws CustomerException 
	{
		boolean isUser = false;
	    try
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();	        
	        String transactionid="8790438398"+transactionId();
    		String bookingid="ID0000"+bookingId();
	        pay.setTransactionid(transactionid);
	        session.persist(pay); 
	        session.getTransaction().commit();
	        log.info("***********Payment is Done Successfully***********");
	        log.info("Your Transaction id is :" + transactionid);
	        log.info(" ");
	        log.info("-------Bus Ticket Booked Successfully-------");
	        log.info("Your booking ID is:" + bookingid);
	        reduceSeats();
	        session.close();
	        isUser = true;
	    } 
	    catch (Exception e) 
	    {
	        log.error("Error inserting payment", e);
	        throw new CustomerException("**********Check your Card Details*********");
	    }
	    return isUser;
	}

	public boolean updateBoardingLocation(String bid, String boardinglocation) throws CustomerException 
	{
		try
	    {
			SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query query = session.createQuery("UPDATE BookTicket SET boardinglocation = :boardingLocation WHERE bid = :custId1");
	        query.setParameter("boardingLocation", boardinglocation);
	        query.setParameter("custId1", bid);
	        int rowsUpdated = query.executeUpdate();
	        session.getTransaction().commit();
	        if (rowsUpdated == 1) 
	        {
	            log.info("***********BoardingLocation Updated Successfully***********");
	        } 
	        else 
	        {
	            throw new CustomerException("**********Sorry Enter Correct BusId!!!*********");
	        }
	        session.close();
	    } 
		catch (Exception e) 
		{
	        throw new CustomerException("Error updating boarding location");
	    }
	    return true; 
	}

	public Map<Integer, BookTicket> viewBookDetailsById(String id) 
	{
	    Map<Integer, BookTicket> booklist = new HashMap<>();
	    try
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query<BookTicket> query = session.createQuery("FROM BookTicket WHERE bid = :custId", BookTicket.class);
	        query.setParameter("custId", id);
	        List<BookTicket> results = query.list();
	        session.getTransaction().commit();
	        int a = 1;
	        for (BookTicket book : results) 
	        {
	            booklist.put(a++, book);
	        }
	        session.close();
	    } 
	    catch (Exception e) 
	    {
	    	log.info("&&"+e.getMessage());
	    }
	    return booklist;
	}

	public void reduceSeats() {
		try
	    {
			SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Bus bus = session.get(Bus.class, HomeBase.booktic.getBusnumber());
	        if (bus != null && bus.getAvaliableseats() > 0) 
	        {
	            int availableSeats = bus.getAvaliableseats() - HomeBase.booktic.getTotalseats();
	            if (availableSeats >= 0) 
	            {
	                bus.setAvaliableseats(availableSeats);
	                session.update(bus);
	            }
	        }
	        session.getTransaction().commit();
	        session.close();
	    } 
		catch (Exception e) 
		{
			log.info("^^"+e.getMessage());
	    }
	}

	public List<Bus> getBusList() {
	    List<Bus> busList = null;
	    try
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query<Bus> query = session.createQuery("FROM Bus", Bus.class);
	        busList = query.list();
	        session.getTransaction().commit();
	        session.close();
	    } 
	    catch (Exception e) 
	    {
	    	log.info("$$"+e.getMessage());
	    }
	    return busList;
	}	
}
