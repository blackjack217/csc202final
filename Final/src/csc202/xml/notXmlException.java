package csc202.xml;

public class notXmlException extends Exception {
	
	public notXmlException(){
		super("The file selected is not in the Xml Format");
	}
	public notXmlException(String s){
		super(s);
	}
}
