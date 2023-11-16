package com.busticket.util;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Validations {
	 static Logger log = Logger.getLogger(Validations.class);
	 static Scanner sc=new Scanner(System.in);
	 private static Validations validations= null;
	 private Validations(){}
	 public static Validations getInstance() 
	 {
		 if(validations == null) validations= new Validations();
			return validations;
	 }
	 
	 public String validateUserName() 
	 {
		 String userName = sc.next();
		 if(Pattern.matches("[a-zA-Z]+.{3}", userName)) return userName;
		 log.info("User name should have only alphabets atleast 3 characters!");
		 return validateUserName();
	 }
	 
	 public  String validatePassword() 
	 {
		 String password = sc.next();
		 if(Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,10})", password)) return password;
		 log.info("Please enter valid password!\nPassword must contain atleast 8 characters\n1 Alphabet lower and upper case\n1 Digit\n1 Special character");
		 return validatePassword();
	 }
	 
	 public  String validateCardNumber()
	 {
		 String card=sc.next();
		 if(Pattern.matches("((?=.*\\d).{12})", card))return card;
		 log.info("Please enter 12 digit card number");
		 return validateCardNumber();
		 
	 }
	 
	 public  String validatedate()
	 {
		 String date=sc.next();
		 if(Pattern.matches("^\\d{2}-\\d{2}-\\d{4}$", date)) return date;
		 log.info("Enter date in format:28-05-2023");
		 return validatedate();
		
		 
	 }
	
	 
	       
	
	

}
