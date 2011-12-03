package csc202.xml;

import java.io.IOException;
import java.util.LinkedList;

import csc202.card.Card;
import csc202.card.FullTicket;
import csc202.card.Items;
import csc202.card.Name;
import csc202.card.Order;

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
	 * @throws notXmlException - Thrown if Path Does not Contain an XML
	 * @throws orderingException - if for some reason the xml because out of order
	 */
	public AddFullTicket(FullTicket make, String filePath) throws IOException, notXmlException, orderingException{
		write.openXml(filePath);
		task(make);
		write.closeWriter();
	}
	
	/**
	 * Used to write more than one xml ticket... or to clean up the xml database
	 * @param list - A linked list of Full Tickets
	 * @param filePath - Location of XML File
	 * @throws IOException - Created by FileWriter
	 * @throws notXmlException - Thrown if Path Does not Contain an XML
	 * @throws orderingException - if for some reason the xml because out of order
	 */
	public AddFullTicket(LinkedList<FullTicket> list, String filePath) throws IOException, notXmlException, orderingException{
		write.openXml(filePath);
		for(int i = 0; i<list.size(); i++){
			task(list.get(i));
		}
		write.closeWriter();
	}
	private void task(FullTicket make) throws IOException, notXmlException, orderingException{
		name = make.getName();
		card = make.getCard();
		order = make.getOrder();
		
		
		write.addFullTicketStart();
		write.addFirstName(name.getFirstName());
		//add middle name after figure out if char == null
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
		//add middle name after figure out if char == null
		write.addLastName(cardHolder.getLastName());
		write.addCcNum(card.getCredit());
		write.addExpDate(card.getExpirationDate().toString()); // this should work... if not i will fix it
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
}
