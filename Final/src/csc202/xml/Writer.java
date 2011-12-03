package csc202.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
	private void checkFile(File file) throws notXmlException{
		int i = file.getName().length() - 1;
		String s = "" + file.getName().charAt(i-2) + file.getName().charAt(i-1) + file.getName().charAt(i);  
		if(!s.equalsIgnoreCase("xml")){
			throw new notXmlException();
		}
	}
	
	/**
	 * Opens the xml file
	 * @param filePath - the path to the xml file
	 * @throws IOException - a error created by FileWriter  
	 * @throws notXmlException - the file found is not in the xml format
	 */
	public void openXml(String filePath) throws IOException, notXmlException{
		checkFile(new File(filePath));
		setWriteFile(new FileWriter(filePath, true));
		setPrintWriter(new PrintWriter(writeFile));
	}
	
	//Checks to see a ticket has started
	private void checkFullTicket() throws orderingException{
		if(!fullTicket){
			throw new orderingException("You have not started a new ticket yet");
		}
	}
	
	//Checks to see a card has started 
	//
	private void checkCard() throws orderingException{
		checkFullTicket();
		if((!card)||(item)||(order)){
			throw new orderingException("You have not Started a new card Yet");
		}
	}
	
	//Checks to see if a card has started
	private void checkOrder() throws orderingException{
		checkFullTicket();
		if((!order)||(card)){
			throw new orderingException("You have not started a new order yet");
		}
	}
	
	//Checks to see if an item has started
	private void checkItem() throws orderingException{
		checkFullTicket();
		checkOrder();
		if((!item)||(card)){
			throw new orderingException("You have not started a new item yet");
		}
	}
	
	/**
	 * Adds the <FullTicket> tag
	 * @throws orderingException - If Full Ticket already Exists
	 */
	public void addFullTicketStart() throws orderingException{
		if(fullTicket){
			throw new orderingException("Full Ticket Already Exists");
		}
		printWriter.format("%s", "<FullTicket>");
		fullTicket = true;
	}
	
	/**
	 * Adds the <Name type="first">name</Name> tag
	 * @param name - First Name 
	 * @throws orderingException - If <FullTicket> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addFirstName(String name) throws orderingException{
		checkFullTicket();
		if((item)||(order)){
			throw new orderingException("Name can't be placed while Item or Order exist");
		}
		else if(card){
			printWriter.format("\t%s%s%s","<Name type=\"first\">",name, "<Name>");
		}
		else{
			printWriter.format("%s%s%s","<Name type=\"first\">",name, "<Name>");
		}
	}
	
	/**
	 * Adds the <Name type="last">name</Name> tag
	 * @param name - Last Name 
	 * @throws orderingException - If <FullTicket> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addLastName(String name) throws orderingException{
		checkFullTicket();
		if((item)||(order)){
			throw new orderingException("Name can't be placed while Item or Order exist");
		}
		else if(card){
			printWriter.format("\t%s%s%s","<Name type=\"last\">",name, "<Name>");
		}
		else{
			printWriter.format("%s%s%s","<Name type=\"last\">",name, "<Name>");
		}
	}
	
	/**
	 * Adds the <Name type="middle">name</Name> tag
	 * @param name - Middle Name 
	 * @throws orderingException - If <FullTicket> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addMiddleName(String name) throws orderingException{
		checkFullTicket();
		if((item)||(order)){
			throw new orderingException("Name can't be placed while Item or Order exist");
		}
		else if(card){
			printWriter.format("\t%s%s%s","<Name type=\"middle\">",name, "<Name>");
		}
		else{
			printWriter.format("%s%s%s","<Name type=\"middle\">",name, "<Name>");
		}
	}
	
	/**
	 * Adds the <Card> tag
	 * @throws orderingException -If <FullTicket> does not exist this error will occur. Or if <Item>, <Card> or <Order> do exist.
	 */
	public void addCardStart() throws orderingException{
		checkFullTicket();
		if((item)||(order)||(card)){
			throw new orderingException("Name can't be placed while Item or Order exist");
		}
		printWriter.format("%s", "<Card>");
		card = true;
	}
	
	/**
	 * Adds the <ccNumber> number </ccNumber> tag
	 * @param number - The Credit Card Number
	 * @throws orderingException - If <FullTicket> or <Card> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addCcNum(String number) throws orderingException{
		checkCard();
		printWriter.format("\t%s%s%s","<ccNumber>",number, "</ccNumber>");
	}
	
	/**
	 * Adds the <expDate> expDate </expDate> tag
	 * @param expDate - The expiration Date
	 * @throws orderingException - If <FullTicket> or <Card> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addExpDate(String expDate) throws orderingException{
		checkCard();
		printWriter.format("\t%s%s%s","<expDate>",expDate, "</expDate>");
	}
	
	/**
	 * Adds the <verCode> code </verCode> tag
	 * @param code - The expiration Date
	 * @throws orderingException - If <FullTicket> or <Card> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addVerCode(String code) throws orderingException{
		checkCard();
		printWriter.format("\t%s%s%s","<verCode>",code, "</verCode>");
	}
	
	/**
	 * Adds the </Card> tag
	 * @throws orderingException - If <FullTicket> or <Card> does not exist this error will occur. Or if <Item> or <Order> do exist.
	 */
	public void addCardEnd() throws orderingException{
		checkCard();
		printWriter.format("%s", "</Card>");
		card = false;
	}
	
	/**
	 *  Adds the <Order> tag
	 * @throws orderingException - If <FullTicket> does not exist this error will occur. Or if <Item>, <Card>, or <Order> do exist.
	 */
	public void addOrderStart() throws orderingException{
		checkFullTicket();
		if((item)||(card)||(order)){
			throw new orderingException("Item card or ticket may already exist");
		}
		printWriter.format("%s", "<Order>");
		order = true;
	}
	
	/**
	 * Adds the <orderNum> number </orderNum>
	 * @param number - Order number 
	 * @throws orderingException - If <FullTicket> or <Order> does not exist this error will occur. Or if <Item> or <Card> do exist.
	 */
	public void addOrderNum(String number) throws orderingException{
		checkOrder();
		if(item){
			throw new orderingException("Item may already exist");
		}
		printWriter.format("\t%s%s%s","<orderNum>",number, "</orderNum>");
	}
	
	/**
	 * Adds the <orderDate> date </orderDate>
	 * @param date - Order Date
	 * @throws orderingException - If <FullTicket> or <Order> does not exist this error will occur. Or if <Item> or <Card> do exist.
	 */
	public void addOrderDate(String date) throws orderingException{
		checkOrder();
		if(item){
			throw new orderingException("Item may already exist");
		}
		printWriter.format("\t%s%s%s","<orderDate>",date, "</orderDate>");
		
	}
	
	/**
	 * Adds <Item> tag
	 * @throws orderingException - If <FullTicket> or <Order> does not exist this error will occur. Or if <Item> or <Card> do exist.
	 */
	public void addItemStart() throws orderingException{
		checkOrder();
		if(item){
			throw new orderingException("Item may already exist");
		}
		printWriter.format("\t%s", "<Item>");
		item = true;
	}
	
	/**
	 * Adds <itemName> name </itemName>
	 * @param name - Name of the Item
	 * @throws orderingException - If <FullTicket>, <Item> or <Order> does not exist this error will occur. Or if <Card> do exist.
	 */
	public void addItemName(String name) throws orderingException{
		checkItem();
		printWriter.format("\t\t%s%s%s","<itemName>",name, "</itemName>");
	}
	
	/**
	 * Adds <Price> price </Price> tag
	 * @param price - The Price of the Item
	 * @throws orderingException - If <FullTicket>, <Item> or <Order> does not exist this error will occur. Or if <Card> do exist.
	 */
	public void addItemPrice(String price) throws orderingException{
		checkItem();
		printWriter.format("\t\t%s%s%s","<Price>",price, "</Price>");
	}
	
	/**
	 * Adds <itemQuantity> num </itemQuantity> tag
	 * @param num - the number of this type of item
	 * @throws orderingException - If <FullTicket>, <Item> or <Order> does not exist this error will occur. Or if <Card> do exist.
	 */
	public void addItemQuantity(String num) throws orderingException{
		checkItem();
		printWriter.format("\t\t%s%s%s","<itemQuantity>",num, "</itemQuantity>");
	}
	
	/**
	 * Adds </Item> tag
	 * @throws orderingException - If <FullTicket>, <Item> or <Order> does not exist this error will occur. Or if <Card> do exist.
	 */
	public void addItemEnd() throws orderingException{
		checkItem();
		printWriter.format("\t%s", "</Item>");
		item = false;
	}
	
	/**
	 * Adds </Order> tag
	 * @throws orderingException If <FullTicket> and <Order> do exist this error will not occur.  Or if <Card> or <Item> do exist.
	 */
	public void addOrderEnd() throws orderingException{
		checkOrder();
		if(item){
			throw new orderingException("Item Still Exists");
		}
		printWriter.format("%s", "</Order>");
		order = false;
	}
	
	/**
	 * Adds </FullTicket> tag
	 * @throws orderingException -
	 */
	public void addFullTicketEnd() throws orderingException{
		checkFullTicket();
		
		printWriter.format("%s", "</FullTicket>");
		fullTicket = false;
	}
	
	/**
	 * Closes the file writer
	 * @throws IOException - created by file writer
	 * @throws orderingException - If <FullTicket>,<Item>,<Card> or <Order> still exist
	 */
	public void closeWriter() throws IOException, orderingException{
		if((!fullTicket)&&(!card)&&(!item)&&(!order)){
			writeFile.close();
		}
		else{
			throw new orderingException("One or more tags many not be closed");
		}
			
	}
	private void setWriteFile(FileWriter fileWriter) {
		this.writeFile = fileWriter;
	}
	private void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}

}
