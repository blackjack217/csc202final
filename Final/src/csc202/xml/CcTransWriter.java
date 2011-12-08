package csc202.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import csc202.security.SafeString;
/**
 * Writes the xml for bank trans
 * @author Justin Jansen
 * 12/7/11
 * 
 * Comments for this class coming soon
 */
public class CcTransWriter {
	private FileWriter writeFile;
	private PrintWriter printWriter;
	
	private boolean started = false;
	private boolean transaction = false;
    	
	public void newXml(String filePath) throws IOException{
		setWriteFile(new FileWriter(filePath, true));
		setPrintWriter(new PrintWriter(writeFile));
		printWriter.format("%s\n", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		started = true;
	}
	private void checkStarted() throws OrderingException{
		if(!started){
			throw new OrderingException("Not Started");
		}
	}
	private void checkTranStart() throws OrderingException{
		checkStarted();
		if(!transaction){
			throw new OrderingException("No Transaction Started");
		}
	}
	public void addTransactionStart(int type) throws OrderingException{
		String card = null;
		switch(type){
		case -1: card = "unknown"; break; 
		case 0: card = "visa"; break;
		case 1: card = "mastercard"; break;
		case 2: card = "amex"; break;
		case 3: card = "discover"; break;
		default: card = "unknown";
		}
		checkStarted();
		printWriter.format("%s%s%s%s\n", "<Transaction type=","\"",card,"\">");
		transaction = true;
	}
	public void addTransactionEnd() throws OrderingException{
		checkTranStart();
		printWriter.format("%s\n", "</Transaction>");
	}
	public void addFirstName(String name) throws OrderingException{
		checkTranStart();
		printWriter.format("%s%s%s\n", "<Name =\"first\">",name,"</Name>");

	}
	public void addMiddleName(char name) throws OrderingException{
		checkTranStart();
		printWriter.format("%s%s%s\n", "<Name =\"middle\">",name,"</Name>");

	}
	public void addLastName(String name) throws OrderingException{
		checkTranStart();
		printWriter.format("%s%s%s\n", "<Name =\"last\">",name,"</Name>");

	}
	public void addCardNumber(SafeString safeString) throws OrderingException{
		checkTranStart();
		printWriter.format("%s%s%s\n", "<Number>",safeString,"</Number>");
	}
	public void addExpDate(String date) throws OrderingException{
		checkTranStart();
		printWriter.format("%s%s%s\n", "<Date>",date,"</Date>");
	}
	public void addVerCode(SafeString safeString) throws OrderingException{
		checkTranStart();
		printWriter.format("%s%s%s\n", "<Ver>",safeString,"</Ver>");
	}
	public void addAmount(double amount) throws OrderingException{
		checkTranStart();
		printWriter.format("%s%d%s\n", "<Amount>", amount, "</Amount>");
	}
	private void setWriteFile(FileWriter fileWriter) {
		this.writeFile = fileWriter;
	}
	private void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
	public void closeWriter() throws IOException, OrderingException{
		if((!started)&&(!transaction)){
			writeFile.close();
		}
		else{
			throw new OrderingException("One or more tags many not be closed");
		}
	}
	public void addExpDate(SafeString safeString) {
		// TODO Auto-generated method stub
		
	}
}
