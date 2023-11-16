package com.busticket.exceptions;

public class BusException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5744129015461282151L;

	public  BusException(){}
	
	public BusException(String str)
	{
		super(str);
	}
	

}
