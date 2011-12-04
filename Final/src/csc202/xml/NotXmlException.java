package csc202.xml;

public class NotXmlException extends Exception {
	
	public NotXmlException(){
		super("The file selected is not in the Xml Format");
	}
	public NotXmlException(String s){
		super(s);
	}
}
