package com.busticket.base;

import java.util.Scanner;
import org.apache.log4j.Logger;

import com.busticket.model.BookTicket;
import com.busticket.model.Bus;
import com.busticket.model.Customer;
import com.busticket.model.Payment;
import com.busticket.util.Validations;

public class HomeBase {
	Customer c=new Customer();
	private static Scanner sc = new Scanner(System.in);
	static Logger log = Logger.getLogger(HomeBase.class);
	private static HomeBase homebaseInstance= null;
	static AdminBase adminBase = AdminBase.getInstance();
	static UserBase userBase = UserBase.getInstance();
	static Validations validations=Validations.getInstance();
	public  static final BookTicket booktic=new BookTicket();
	public static  HomeBase getInstance() 
	{
		if(homebaseInstance == null) homebaseInstance= new HomeBase();
		return homebaseInstance;
	}
	public void adminmethods()
	{
		log.info("*********************************************************");
		log.info("*\t\t\t Welcome Admin \t\t\t*");
		log.info("*********************************************************");
		log.info("1.Add Bus");
		log.info("2.Delete Bus");
		log.info("3.Update Bus By date");
		log.info("4.View Bus Details on that date");
		log.info("5.Update Bus by Avaliableseats");
		log.info("6.View All Tickets");
		log.info("7.Exit");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			Bus bus = new Bus();
				log.info("Enter BusNo");
				bus.setBusno(sc.nextInt());
				log.info("Enter Bus date-Ex(28-05-2023)");
				bus.setDate(sc.next());
				log.info("Enter bname");
				bus.setBname(sc.next());
				log.info("Enter bus routefrom");
				bus.setRoutefrom(sc.next());
				log.info("Enter bus routeto");
				bus.setRouteto(sc.next());
				log.info("Enter boardingpoints");
				bus.setBoardingpoint(sc.next());
				log.info("Enter arrivalpoints");
				bus.setArrivingpoint(sc.next());
				log.info("Enter btype(AC/NonAC)");
				bus.setBtype(sc.next());
				log.info("Enter arrival");
				bus.setArrival(sc.next());
				log.info("Enter departure");
				bus.setDeparture(sc.next());
				log.info("Enter totalseats");
				bus.setTotalseats(sc.nextInt());
				log.info("Enter Avaliableseats");
				bus.setAvaliableseats(sc.nextInt());
				log.info("Enter amount");
				bus.setAmount(sc.nextInt());
				adminBase.addBus(bus);
			break;
		case 2:
			log.info("Enter Number");
			int num=sc.nextInt();
			adminBase.deleteBus(num);
			break;
		case 3:
			log.info("Enter busno");
			int busnumber=sc.nextInt();
			log.info("Enter date EX:30-05-2023");
			String busdate=sc.next();
			adminBase.updateBus(busnumber, busdate);
			break;
		case 4:
			log.info("Enter your date EX:30-05-2023");
			String bussdate=sc.next();
			adminBase.viewBookingDetails(bussdate);
			break; 
		case 5:
			log.info("Enter Busno");
			int bn=sc.nextInt();
			log.info("Enter Avaliable Seats");
		    int as=sc.nextInt();
		    adminBase.updateAvaliableSeats(bn,as);
			break;
		case 6:
			log.info("Enter getalltickets date EX:30-05-2023");
			String date=sc.next();
			adminBase.getAllTickets(date);
			break;
		case 7:
			log.info("Logout Successfully!!!!");
			break;
		default:
			log.info("Invalid");
			break;
		}
	}
	
	public  void usermethods()
	{
		log.info("#*******************************************************#");
		log.info("*\t\t\t Welcome User \t\t\t*");
		log.info("#*******************************************************#");
		log.info("1.View Buses by routefrom and routeto and date");
		log.info("2.Delete Bus Ticket");
	    log.info("3.View Booking Details by id");
		log.info("4.Update Boarding Location");
		log.info("5.Exit");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			log.info("Enter your routefrom");
			String routefrom=sc.next();
			log.info("Enter your routeto");
			String routeto=sc.next();
			log.info("Enter date-Ex(28-05-2023)");
			String date=validations.validatedate();
			userBase.viewbusdetails(routefrom,routeto,date);
			break;
		case 2:
			log.info("Enter your custid");
			String id=sc.next();
			userBase.deleteBusTicket(id);
			break;
		case 3:
			log.info("Enter Customer Id");
			String cid=sc.next();
			userBase.viewBookingDetails(cid);
			break;
		case 4:
			log.info("Enter Bus Id");
			String bid=sc.next();
			log.info("Enter Boarding Location");
			String boarding=sc.next();
			userBase.updateBoardingLocation(bid, boarding);
			break; 
		case 5:
			log.info("Logout Successfully!!!!");
			break;
		default:
			log.info("Invalid");
			break;
		}
		
	}
	
	public void bookticket()
	{
		log.info("Enter Bus Number");
		booktic.setBusnumber(sc.nextInt());
		log.info("Enter bookticket routefrom");
		booktic.setRoutefrom(sc.next());
		log.info("Enter bookticket routeto");
		booktic.setRouteto(sc.next());
		log.info("Enter date EX:30-05-2023");
		booktic.setDate(validations.validatedate());
		log.info("Enter Your Name");
		booktic.setName(sc.next());
		log.info("Enter your Mobile Number");
		booktic.setMobile(sc.next());
		log.info("enter number of seats");
		booktic.setTotalseats(sc.nextInt());
		log.info("Enter Boarding Location");
		booktic.setBoardinglocation(sc.next());
		log.info("Enter Arrival Location");
		booktic.setArrivinglocation(sc.next());
		userBase.bookTicket(booktic);
		payment();
	}
	
	public void payment()
	{
		Payment pay=new Payment();
		log.info("--------Do The Payment---------");
		log.info("Enter card Holder Name");
		pay.setCardholdername(sc.next());
		log.info("Enter card Number");
		pay.setCardnumber(validations.validateCardNumber());
		log.info("Enter cvv");
		pay.setCvv(sc.nextInt());
		log.info("Enter Expiry date");
		pay.setExpirydate(sc.next());
		userBase.payment(pay);
		
	}

}
