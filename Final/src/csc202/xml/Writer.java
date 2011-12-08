package csc202.xml;

import csc202.security.SafeString;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import csc202.xml.NotXmlException;
import csc202.xml.OrderingException;

/**
 * 
 * @author Justin Jansen
 * Date: 12/2/11
 *
 * Notes: In it's current state all methods related to xml tags take Strings, I did this because I assumed that most of the information
 * will be generated using a gui, and therefore is already in string format.  This can be easily changed. 
 * 
 */

public class Writer {
	
	private FileWriter writeFile;
	private PrintWriter printWriter;
	
	private boolean fullTicket = false;
	private boolean card = false;
	private boolean item = false;
	private boolean order = false;
	
	
	//Checks to see if the file is an XML file
	private void checkFile(File file) throws NotXmlException  {
		int i = file.getName().length() - 1;
		String s = "" + file.getName().charAt(i-2) + file.getName().charAt(i-1) + file.getName().charAt(i);  
		if(!s.equalsIgnoreCase("xml")){
			throw new NotXmlException();
		}
	}
	
	/**
	 * Opens the xml file
	 * @param filePath - the path to the xml file
	 * @throws IOException - a error created by FileWriter  
	 * @throws NotXMLException - the file found is not in the xml format
	 */
	public void openXml(String filePath) throws IOException, NotXmlException {
		checkFile(new File(filePath));
		setWriteFile(new FileWriter(filePath, true));
		setPrintWriter(new PrintWriter(writeFile));
	}
	
	//Checks to see a ticket has started
	private void checkFullTicket() throws OrderingException {
		if(!fullTicket){
			throw new OrderingException("You have not started a new ticket yet");
		}
	}
	
	//Checks to see a card has started 
	//
	private void checkCard() throws OrderingException {
		checkFullTicket();
		if((!card)||(item)||(order)){
			throw new OrderingException("You have not Started a new card Yet");
		}
	}
	
	//Checks to see if a card has started
	private void checkOrder() throws OrderingException {
		checkFullTicket();
		if((!order)||(card)){
			throw new OrderingException("You have not started a new order yet");
		}
	}
	
	//Checks to see if an item has started
	private void checkItem() throws OrderingException {
		checkFullTicket();
		checkOrder();
		if((!item)||(card)){
			throw new OrderingException("You have not started a new item yet");
		}
	}
	
	/**
	 * Adds the <FullTicket> tag
	 * @throws OrderingException - If Full Ticket already Exists
	 */
	public void addFullTicketStart() throws OrderingException {
		if(fullTicket){
			throw new OrderingException("Full Ticket Already Exists");
		}
		printWriter.format("%s\n", "<FullTicket>");
		fullTicket = true;
	}
	
	/**
	 * Adds the <Name type="first">name</Name> tag
	 * @param name - First Name 
	 * @throws OrderingException - If <FullTicket> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addFirstName(String name) throws OrderingException {
		checkFullTicket();
		if((item)||(order)){
			throw new OrderingException("Name can't be placed while Item or Order exist");
		}
		else if(card){
			printWriter.format("\t%s%s%s\n","<Name type=\"first\">",name, "<Name>");
		}
		else{
			printWriter.format("%s%s%s\n","<Name type=\"first\">",name, "<Name>");
		}
	}
	
	/**
	 * Adds the <Name type="last">name</Name> tag
	 * @param name - Last Name 
	 * @throws OrderingException - If <FullTicket> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addLastName(String name) throws OrderingException {
		checkFullTicket();
		if((item)||(order)){
			throw new OrderingException("Name can't be placed while Item or Order exist");
		}
		else if(card){
			printWriter.format("\t%s%s%s\n","<Name type=\"last\">",name, "<Name>");
		}
		else{
			printWriter.format("%s%s%s\n","<Name type=\"last\">",name, "<Name>");
		}
	}
	
	/**
	 * Adds the <Name type="middle">name</Name> tag
	 * @param c - Middle Name 
	 * @throws OrderingException - If <FullTicket> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addMiddleName(char c) throws OrderingException {
		checkFullTicket();
		if((item)||(order)){
			throw new OrderingException("Name can't be placed while Item or Order exist");
		}
		else if(card){
			printWriter.format("\t%s%s%s\n","<Name type=\"middle\">",c, "<Name>");
		}
		else{
			printWriter.format("%s%s%s\n","<Name type=\"middle\">",c, "<Name>");
		}
	}
	
	/**
	 * Adds the <Card> tag
	 * @param - Type of card: visa mastercard amex 
	 * @throws OrderingException -If <FullTicket> does not exist this error will occur. Or if <Item>, <Card> or <Order> do exist.
	 */
	public void addCardStart(String type) throws OrderingException {
		checkFullTicket();
		if((item)||(order)||(card)){
			throw new OrderingException("Name can't be placed while Item or Order exist");
		}
		printWriter.format("%s%s%s\n", "<Card type=\"", type,"\">");
		card = true;
	}
	
	/**
	 * Adds the <ccNumber> number </ccNumber> tag
	 * @param safeString - The Credit Card Number
	 * @throws OrderingException - If <FullTicket> or <Card> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addCcNum(SafeString safeString) throws OrderingException {
		checkCard();
		printWriter.format("\t%s%s%s\n","<ccNumber>",safeString, "</ccNumber>");
	}
	
	/**
	 * Adds the <expDate> expDate </expDate> tag
	 * @param expDate - The expiration Date
	 * @throws OrderingException - If <FullTicket> or <Card> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addExpDate(String expDate) throws OrderingException {
		checkCard();
		printWriter.format("\t%s%s%s\n","<expDate>",expDate, "</expDate>");
	}
	
	/**
	 * Adds the <verCode> code </verCode> tag
	 * @param safeString - The expiration Date
	 * @throws OrderingException - If <FullTicket> or <Card> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addVerCode(SafeString safeString) throws OrderingException {
		checkCard();
		printWriter.format("\t%s%s%s\n","<verCode>",safeString, "</verCode>");
	}
	
	/**
	 * Adds the </Card> tag
	 * @throws OrderingException - If <FullTicket> or <Card> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addCardEnd() throws OrderingException {
		checkCard();
		printWriter.format("%s\n", "</Card>");
		card = false;
	}
	
	/**
	 *  Adds the <Order> tag
	 * @throws OrderingException - If <FullTicket> does not exist this error will occur. Or if <Item>, <Card>, or <Order> do exist.
	 */
	public void addOrderStart() throws OrderingException {
		checkFullTicket();
		if((item)||(card)||(order)){
			throw new OrderingException("Item card or ticket may already exist");
		}
		printWriter.format("%s\n", "<Order>");
		order = true;
	}
	
	/**
	 * Adds the <orderNum> number </orderNum>
	 * @param number - Order number 
	 * @throws OrderingException - If <FullTicket> or <Order> does not exist this error will occur. Or if <Item> or <Card> do exist.
	 */
	public void addOrderNum(String number) throws OrderingException {
		checkOrder();
		if(item){
			throw new OrderingException("Item may already exist");
		}
		printWriter.format("\t%s%s%s\n","<orderNum>",number, "</orderNum>");
	}
	
	/**
	 * Adds the <orderDate> date </orderDate>
	 * @param date - Order Date
	 * @throws OrderingException - If <FullTicket> or <Order> does not exist this error will occur. Or if <Item> or <Card> do exist.
	 */
	public void addOrderDate(String date) throws OrderingException {
		checkOrder();
		if(item){
			throw new OrderingException("Item may already exist");
		}
		printWriter.format("\t%s%s%s\n","<orderDate>",date, "</orderDate>");
		
	}
	
	/**
	 * Adds <Item> tag
	 * @throws OrderingException - If <FullTicket> or <Order> does not exist this error will occur. Or if <Item> or <Card> do exist.
	 */
	public void addItemStart() throws OrderingException {
		checkOrder();
		if(item){
			throw new OrderingException("Item may already exist");
		}
		printWriter.format("\t%s\n", "<Item>");
		item = true;
	}
	
	/**
	 * Adds <itemName> name </itemName>
	 * @param name - Name of the Item
	 * @throws OrderingException - If <FullTicket>, <Item> or <Order> does not exist this error will occur. Or if <Card> do exist.
	 */
	public void addItemName(String name) throws OrderingException {
		checkItem();
		printWriter.format("\t\t%s%s%s\n","<itemName>",name, "</itemName>");
	}
	
	/**
	 * Adds <Price> price </Price> tag
	 * @param price - The Price of the Item
	 * @throws OrderingException - If <FullTicket>, <Item> or <Order> does not exist this error will occur. Or if <Card> do exist.
	 */
	public void addItemPrice(String price) throws OrderingException {
		checkItem();
		printWriter.format("\t\t%s%s%s\n","<Price>",price, "</Price>");
	}
	
	/**
	 * Adds <itemQuantity> num </itemQuantity> tag
	 * @param num - the number of this type of item
	 * @throws OrderingException - If <FullTicket>, <Item> or <Order> does not exist this error will occur. Or if <Card> do exist.
	 */
	public void addItemQuantity(String num) throws OrderingException {
		checkItem();
		printWriter.format("\t\t%s%s%s\n","<itemQuantity>",num, "</itemQuantity>");
	}
	
	/**
	 * Adds </Item> tag
	 * @throws OrderingException - If <FullTicket>, <Item> or <Order> does not exist this error will occur. Or if <Card> do exist.
	 */
	public void addItemEnd() throws OrderingException {
		checkItem();
		printWriter.format("\t%s\n", "</Item>");
		item = false;
	}
	
	/**
	 * Adds </Order> tag
	 * @throws OrderingException If <FullTicket> and <Order> do exist this error will not occur.  Or if <Card> or <Item> do exist.
	 */
	public void addOrderEnd() throws OrderingException {
		checkOrder();
		if(item){
			throw new OrderingException("Item Still Exists");
		}
		printWriter.format("%s\n", "</Order>");
		order = false;
	}
	
	/**
	 * Adds </FullTicket> tag
	 * @throws OrderingException -
	 */
	public void addFullTicketEnd() throws OrderingException {
		checkFullTicket();
		
		printWriter.format("%s\n", "</FullTicket>");
		fullTicket = false;
	}
	
	/**
	 * Closes the file writer
	 * @throws IOException - created by file writer
	 * @throws OrderingException - If <FullTicket>,<Item>,<Card> or <Order> still exist
	 */
	public void closeWriter() throws IOException, OrderingException {
		if((!fullTicket)&&(!card)&&(!item)&&(!order)){
			writeFile.close();
		}
		else{
			throw new OrderingException("One or more tags many not be closed");
		}
			
	}
	private void setWriteFile(FileWriter fileWriter) {
		this.writeFile = fileWriter;
	}
	private void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}

}
