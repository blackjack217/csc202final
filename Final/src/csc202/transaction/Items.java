package csc202.transaction;
/**
 * Class used for Order.java Designed to hold the items within the order. 
 * @author Justin Jansen
 * Date: 11/30/11
 */
public class Items {
	private String item;
	private double cost;
	private int quantity;
	/**
	 * For Adding just one of an item
	 * @param item - The name of the item
	 * @param cost - The price of the item
	 */
	public Items(String item, double cost){
		this.item = item;
		this.cost = cost;
		quantity = 1;
	}
	/**
	 * For Adding more than one of a certain item
	 * @param item - The name of the item
	 * @param cost - The Price of the item
	 * @param quantity - The Number of items
	 */
	public Items(String item, double cost, int quantity) throws Exception{
		if(quantity<=0){
			throw new Exception("No Negitives");
		}
		else{
			this.item = item;
			this.cost = cost;
			this.quantity = quantity;
		}
	}
	/**
	 * Set Item Name
	 * @param s - Item Name
	 */
	public void setItem(String s){
		item = s;
	}
	/**
	 * Get item name
	 * @return returns the name of the item
	 */
	public String getItem(){
		return item;
	}
	/**
	 * Set price of the Item
	 * @param d - The price of the item
	 */
	public void setCost(double d){
		cost = d;
	}
	/**
	 * Get the price of the item
	 * @return - The price of the item
	 */
	public double getCost(){
		return cost;
	}
	/**
	 * Set the number of items
	 * @param i - Set the number of items
	 */
	public void setQuantity(int i){
		quantity = i;
	}
	/**
	 * Get the quantity of this item
	 * @return - The quantity of this item
	 */
	public int getQuanity(){
		return quantity;
	}
	/**
	 * Get the combined price 
	 * @return - The Combined price 
	 */
	public double getTotalPrice(){
		return cost * (double)quantity;
	}
}
