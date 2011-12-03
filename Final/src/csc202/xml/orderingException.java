package csc202.xml;

public class orderingException extends Exception {
	
	public orderingException(){
		super("An Ordering Error has Occured");
	}
	public orderingException(String s){
		super(s);
	}
	
}
