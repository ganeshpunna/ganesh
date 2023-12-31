package com.busticket.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil 
{
	private static SessionFactory sessionFactory;
	
	static {
		try 
		{
 			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
				sessionFactory = configuration.buildSessionFactory(); 
		}	
		 	
		catch (Exception ex) 
		{
			throw new ExceptionInInitializerError(ex);
		}	
	}

	public static SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}
}
