package csc202.xml;

import csc202.card.Card;
import csc202.card.Name;
import csc202.transaction.FullTicket;
import csc202.transaction.Items;
import csc202.transaction.Order;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * 
 * @author Justin
 * Date: 12/3/11
 */
public class AddFullTicket {
		
	private Name name;
	private Card card;
	private Order order;
	private Writer write = new Writer();
	
	/**
	 * Adds A Single ticket to XML File
	 * @param make - A Full Ticket 
	 * @param filePath - Location of XML File
	 * @throws IOException - Created by FileWriter
	 * @throws NotXMLException - Thrown if Path Does not Contain an XML
	 * @throws OrderingException - if for some reason the xml because out of order
	 */
	public AddFullTicket(FullTicket make, String filePath) throws IOException, NotXmlException, OrderingException  {
		write.openXml(filePath);
		task(make);
		write.closeWriter();
	}
	
	/**
	 * Used to write more than one xml ticket... or to clean up the xml database
	 * @param list - A linked list of Full Tickets
	 * @param filePath - Location of XML File
	 * @throws IOException - Created by FileWriter
	 * @throws NotXMLException - Thrown if Path Does not Contain an XML
	 * @throws OrderingException - if for some reason the xml because out of order
	 */
	public AddFullTicket(LinkedList<FullTicket> list, String filePath) throws IOException, NotXmlException, OrderingException {
		write.openXml(filePath);
		for(int i = 0; i<list.size(); i++){
			task(list.get(i));
		}
		write.closeWriter();
	}
	private void task(FullTicket make) throws IOException, NotXmlException, OrderingException {
		name = make.getName();
		card = make.getCard();
		order = make.getOrder();
		
		
		write.addFullTicketStart();
		write.addFirstName(name.getFirstName());
		if (name.getMiddleName()!='\u0000'){
			write.addMiddleName(name.getMiddleName());
		}
		write.addLastName(name.getLastName());
		
		int i = card.getCardType();
		String type = null;
		switch(i){
		case -1: type = "unknown"; break; 
		case 0: type = "visa"; break;
		case 1: type = "mastercard"; break;
		case 2: type = "amex"; break;
		case 3: type = "discover"; break;
		}
		
		write.addCardStart(type);
		Name cardHolder = card.getName();
		write.addFirstName(cardHolder.getFirstName());
		if (cardHolder.getMiddleName()!='\u0000'){
			write.addMiddleName(cardHolder.getMiddleName());
		}
		write.addLastName(cardHolder.getLastName());
		write.addCcNum(card.getCredit());
		write.addExpDate(dateFixer(card.getExpirationDate())); 
		write.addVerCode(card.getVerification());
		write.addCardEnd();
		write.addOrderStart();
		write.addOrderNum(order.getNumber());
		write.addOrderDate(order.getOrderDate());
		LinkedList<Items> items = order.getItemsList();
		for(int j = 0; j<items.size(); j++){
			write.addItemStart();
			write.addItemName(items.get(j).getItem());
			String s = "" + items.get(j).getCost();
			write.addItemPrice(s);
			s = "" + items.get(j).getQuanity();
			write.addItemQuantity(s);
			write.addItemEnd();
		}
		write.addOrderEnd();
		write.addFullTicketEnd();
	}
	private String dateFixer(Date date){
		DateFormat sdf = new SimpleDateFormat("MM-yy");
		return sdf.format(date);
		
	}
	
}
