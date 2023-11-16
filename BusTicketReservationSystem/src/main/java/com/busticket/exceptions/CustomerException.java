package com.busticket.exceptions;

public class CustomerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5151020696428968965L;

	public  CustomerException(){}
	
	public CustomerException(String str)
	{
		super(str);
	}

}
