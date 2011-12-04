package csc202.xml;

public class OrderingException extends Exception {
	
	public OrderingException(){
		super("An Ordering Error has Occured");
	}
	public OrderingException(String s){
		super(s);
	}
	
}
