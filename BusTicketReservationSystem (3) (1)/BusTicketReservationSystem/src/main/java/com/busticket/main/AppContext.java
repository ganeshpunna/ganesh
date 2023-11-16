package com.busticket.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.busticket.base.AdminBase;
import com.busticket.base.UserBase;
import com.busticket.model.Customer;
import com.busticket.util.Validations;

public class AppContext {
	    Customer c=new Customer();
	    private static Scanner sc=new Scanner(System.in);
		Logger log = Logger.getLogger(AppContext.class);
		static AdminBase admincontroller = AdminBase.getInstance();
		static UserBase usercontroller = UserBase.getInstance();
		static Validations validations=Validations.getInstance();
		boolean temp=true;
		public  void main() {
		do {
			log.info("*************************************************************");
			log.info("Welcome to Bus Ticket Management System");
			log.info("1.Admin Login");
			log.info("2.User Login");
			log.info("3.User Register");
			log.info("4.exit");
			
			int option = sc.nextInt();
			switch (option) {
			case 1:
					log.info("Enter Admin Username");
					String uname=sc.next();
					log.info("Enter password");
					String pass=sc.next();
					admincontroller.adminLogin(uname,pass);
				break;
			case 2:
				log.info("Enter Customer Username");
				String useruname=sc.next();
				log.info("Enter Password");
				String userpass=sc.next();
				usercontroller.userLogin(useruname,userpass);
				break;
			case 3:
				  log.info("Enter Username:");
				  String u=validations.validateUserName();
				  log.info("Enter Password:");
				  log.info("Please enter valid password!\nPassword must contain atleast 8 characters\n1 Alphabet lower and upper case\n1 Digit\n1 Special character");
				  String p=validations.validatePassword();
				  log.info("Enter firstname");
				  String fn=sc.next();
				  log.info("Enter lastname");
				  String ln=sc.next();
				  log.info("Enter mobile number");
				  String m=sc.next();
				  usercontroller.userSignUp(u,p,fn,ln,m);
				break;
			case 4:
				  log.info("Logout Successfully");
				break;

			default:
				log.info("Invalid");
				break;
			}
		}while(temp);
	}

		public static void main(String[] args)
	    {
		AppContext b = new AppContext();
		b.main();

	    }

}
