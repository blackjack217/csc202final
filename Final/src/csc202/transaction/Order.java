package csc202.transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * This will contain the order information.
 * @author Justin Jansen
 * Date: 11/30/11
 */
public class Order implements Comparable<Order>{
	private String orderNumber = "";
	private Calendar date = Calendar.getInstance();
	private SimpleDateFormat dateForm = new SimpleDateFormat("'Date:' yyyy.MM.dd '\nTime:' hh:mm:ss a zzz");
	private LinkedList<Items> items = new LinkedList<Items>();
	/**
	 * Order Number
	 * @param number - Order Number
	 */
	public void setNumber(String number){
		orderNumber = number;
		dateForm.format(date.getTime());
	}
	/**
	 * @return Order Number
	 */
	public String getNumber(){
		return orderNumber;
	}
	/**
	 * Adding and item with quantity of one
	 * @param item - Name
	 * @param cost - Price
	 */
	// TODO Check list for Item If Item is Already On list Update Quantity
	public void addItem(String item, double cost){
		Items i = new Items(item,cost);
		items.add(i);
	}
	/**
	 * Adding Item with quantity more than one
	 * @param item - Name 
	 * @param cost - Price
	 * @param quantity - Number of Items
	 * @throws Exception - if quantity is negative
	 */
	
	// TODO Check list for Item If Item is Already On list Update Quantity
	public void addItem(String item, double cost, int quantity) throws Exception{
		Items i = new Items(item, cost, quantity);
		items.add(i);
	}
	/**
	 * 
	 * @param item - Item Name
	 * @throws Exception - 
	 */
	public void removeItem(String item) throws Exception{
		// TODO write this
		if(true){
			//remove
		}
		else{
			throw new Exception("Item Not Found");
		}
	}
	/**
	 * Number of Items
	 * @return Returns the number of times, not including the quantity of each type of item
	 */
	public int numberOfItems(){
		return items.size();
	}
	/**
	 * @return the total Number including quantity
	 */
	public int totalQuantityOfItems(){
		int total = 0;
		for(int i = 0;items.size()>i;i++){
			total =+ items.get(i).getQuanity();
		}
		return total;
	}
	/**
	 * Sets The Time and Date to Current Time and Date
	 */
	public void resetDate(){
		dateForm.format(date.getTime());
	}
	/**
	 * @return The Entire Linked List of items
	 */
	public LinkedList<Items> getItemsList(){
		return items;
	}
	public String getOrderNumber(){
		return orderNumber;
	}
	public String getOrderDate(){
		return dateForm.toString(); //Will check if this is correct later
	}

    public double getTotalCost(){
        double price = 0;
        for (Items item : items) {
            price += item.getTotalPrice();
        }
        return price;
    }

	public int compareTo(Order arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
