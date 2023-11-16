package com.busticket.dao;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.busticket.exceptions.BusException;
import com.busticket.model.Admin;
import com.busticket.model.BookTicket;
import com.busticket.model.Bus;
import com.busticket.util.HibernateUtil;

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
	
	public boolean adminLogin(String username, String password) throws BusException {
	    boolean isAdmin = false;
	    try
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query<Admin> query = session.createQuery("FROM Admin WHERE username = :username AND password = :password", Admin.class);
	        query.setParameter("username", username);
	        query.setParameter("password", password);
	        Admin admin = query.uniqueResult();
	        if (admin != null) 
	        {
	            isAdmin = true;
	            log.info("Login Successfully !!!!");
	        } 
	        else 
	        {
	            throw new BusException("Invalid Credentials");
	        }
	        session.close();
 	    } 
	    catch (Exception e) 
	    {
 	    	log.info(e.getMessage());
	    }
	    return isAdmin;
	}
	
	public boolean addBus(Bus bus) throws BusException {
		try(SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();)
	    {
	        session.beginTransaction();
	        session.persist(bus);
	        session.getTransaction().commit();
	        log.info("***********Bus Added Successfully***********");
	        return true;
	    } 
		catch (Exception e) 
		{
	       throw new BusException("**********Sorry, Check your Details*********");
	    }
	}
	
	public boolean deleteBus(int busno) throws BusException {
		try 
	    {
			SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        session.delete(session);
        Query query = session.createQuery("DELETE FROM Bus WHERE busno = :busno");
	        query.setParameter("busno", busno);
	        int rowsDeleted = query.executeUpdate();
	        session.getTransaction().commit();
	        if (rowsDeleted == 1) 
	        {
	            log.info("***********Bus Deleted Successfully***********");
	        } 
	        else 
	        {
	            throw new BusException("**********Sorry, Enter Correct BusNumber*********");
	        }
	        session.close();
	    } 
		catch (Exception e) 
		{
			log.info(e.getMessage());
	        throw new BusException("Error deleting bus");
	    }
	    return true; 
	}
	
	public boolean updateBus(int busno, String date) throws BusException {
		try 
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query query = session.createQuery("UPDATE Bus SET date = :newDate WHERE busno = :busNumber");
	        query.setParameter("newDate", date);
	        query.setParameter("busNumber", busno);
	        int rowsUpdated = query.executeUpdate();
	        session.getTransaction().commit();
	        if (rowsUpdated == 1) 
	        {
	            log.info("***********Bus Details Updated Successfully***********");
	        } 
	        else 
	        {
	            throw new BusException("**********Sorry!!!, Enter Correct BusNumber!!*********");
	        }
	        session.close();
	    } 
		catch (Exception e) 
		{
	        throw new BusException("Error updating bus");
	    }
		return true;
	}
	
	public List<Bus> viewAllBuses(String date)  {
	    List<Bus> busList = new ArrayList<>();
	    try 
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query<Bus> query = session.createQuery("FROM Bus WHERE date = :dateParam", Bus.class);
	        query.setParameter("dateParam", date);
	        busList = query.list();
	        session.getTransaction().commit();
	        session.close();
	    } 
	    catch (Exception e) 
	    {
	    	log.info("----"+e.getMessage());
	    }
	    return busList;
	}
	
	public boolean updateAvailableSeats(int busno, int availableSeats) throws BusException {
		try
	    {
			SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query<Bus> query = session.createQuery("UPDATE Bus SET availableSeats = :availableSeats WHERE busno = :busNumber1",Bus.class);
	        query.setParameter("availableSeats", availableSeats);
	        query.setParameter("busNumber1", busno);
	        int rowsUpdated1 = query.executeUpdate();
	        session.getTransaction().commit();
	        if (rowsUpdated1 == 1) 
	        {
	            log.info("***********#Bus Updated SuccessFully#***********");
	        } 
	        else 
	        {
	            throw new BusException("**********Sorry, Enter Correct BusNumber!!!*********");
	        }
	        session.close();
	    } 
		catch (Exception e) 
		{
	        throw new BusException("Error updating available seats for bus");
	    }
	    return true; 
	}
	
	public List<BookTicket> getAllTickets(String date) {
	    List<BookTicket> bookTickets = new ArrayList<>();
	    try
	    {
	    	SessionFactory factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        session.beginTransaction();
	        Query<BookTicket> query = session.createQuery("FROM BookTicket WHERE date = :dateParam", BookTicket.class);
	        query.setParameter("dateParam", date);
	        bookTickets = query.list();
	        session.getTransaction().commit();
	        session.close();
	    }
	        catch (Exception e) 
		    {
		    	log.info("----"+e.getMessage());
		    }
	   
	    return bookTickets;
	}
}

