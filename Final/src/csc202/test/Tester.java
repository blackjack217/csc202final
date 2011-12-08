package csc202.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import csc202.card.Name;
import csc202.card.*;
import csc202.security.SafeString;
import csc202.transaction.FullTicket;
import csc202.transaction.Order;
import csc202.xml.AddFullTicket;
import csc202.xml.NotXmlException;
import csc202.xml.OrderingException;

public class Tester {
	
	public static void main (String [] agrs){
		Name name0 = new Name("Jon", "Stewart");
		Name name1 = new Name("Steven",'A',"Colbert");
		Visa visa0 = new Visa();
		AmEx Amex1 = new AmEx();
		visa0.setCredit(new SafeString("987654321"));
		Amex1.setCredit(new SafeString("123456789"));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-yy");
			Date date = sdf.parse("01-12");
			visa0.setExpirationDate(date);
			Amex1.setExpirationDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		visa0.setName(name0);
		Amex1.setName(name1);
		visa0.setVerification(new SafeString("123"));
		Amex1.setVerification(new SafeString("321"));
		Order order0 = new Order();
		Order order1 = new Order();
		order0.addItem("Thing0", 9.99);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
			order1.addItem("Thing1", 1.99, 5);
			Date date = sdf.parse("2011.12.07 11:11:11");
			order0.setOrderDate(date);
			order1.setOrderDate(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order0.setNumber("12345");
		order1.setNumber("54321");
		FullTicket ft0 = new FullTicket(name0, visa0, order0);
		FullTicket ft1 = new FullTicket(name1, Amex1, order1);
		LinkedList<FullTicket> ll = new LinkedList<FullTicket>();
		ll.add(ft0);
		ll.add(ft1);
		try {
			AddFullTicket aft0 = new AddFullTicket(ft0, "F:\\csc202final1\\Final\\Example.xml");
			AddFullTicket aft1 = new AddFullTicket(ll,"F:\\csc202final1\\Final\\Example.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotXmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
