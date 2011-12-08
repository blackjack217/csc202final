package csc202.xml;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import csc202.card.Card;
import csc202.card.Name;
import csc202.security.SafeString;
/**
 * Creates the xml to be transfered to the cc checker
 * @author Justin Jansen
 * Date 12/8/11
 * 
 *  Comments for this class coming soon!
 */
public class AddTransaction {
	private Date date = new Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-SS");
	private String stringDate = sdf.format(date);
	private CcTransWriter cc = new CcTransWriter();
	
	/**
	 * 
	 * @param card
	 * @param amount
	 * @param folder
	 * @throws IOException
	 * @throws OrderingException
	 */
	public void newTransaction(Card card, double amount, String folder) throws IOException, OrderingException{
		createXml(card, amount, createFilePath(folder));
	}
	//A file name is generated from the date, and put into the user selected folder
	private String createFilePath(String folder){
		String filePath;
		if((folder.charAt(folder.length()-1)=='\\')||(folder.charAt(folder.length()-1)=='/')){
			filePath = folder+stringDate+".xml";
		}
		else{
			//if the system is windows based use \ else use /
			if(System.getProperty("os.name").toLowerCase().indexOf("win")>=0){
				filePath = folder+"\\"+stringDate+".xml";
			}
			else{
				filePath = folder+"/"+stringDate+".xml";
			}
		}
		return filePath;
	}
	//the actual process of making the xml
	private void createXml(Card card,double amount,String filePath) throws IOException, OrderingException{
		Name name = card.getName();
		cc.newXml(filePath);
		cc.addTransactionStart(card.getCardType());
		cc.addFirstName(name.getFirstName());
		if (name.getMiddleName()!='\u0000'){
			cc.addMiddleName(name.getMiddleName());
		}
		cc.addLastName(name.getLastName());
		cc.addCardNumber(card.getCredit());
		cc.addExpDate(new SafeString(dateFixer(card.getExpirationDate())));
		cc.addVerCode(card.getVerification());
		cc.addAmount(amount);
		cc.addTransactionEnd();
		cc.closeWriter();
	}
	private String dateFixer(Date date){
		DateFormat sdf = new SimpleDateFormat("MM-yy");
		return sdf.format(date);
		
	}
	public String getStringDate(){
		return stringDate;
	}
}

