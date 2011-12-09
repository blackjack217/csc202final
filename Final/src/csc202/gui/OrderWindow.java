package csc202.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.*;

import csc202.transaction.Items;

public class OrderWindow implements ActionListener {

	int WindowHeight = 400;
	int WindowWidth = 680;
	
	JFrame window = new JFrame("Order");
	
	JButton order = new JButton("Order");
	JButton back = new JButton("Back");
	
	JLabel customerFirstNameMsg = new JLabel("First Name:");
	JLabel customerLastNameMsg = new JLabel("Last Name:");
	JLabel nameOnCardMsg = new JLabel("Name On Card:");
	JLabel cardNumberMsg = new JLabel("Card Number:");
	JLabel securityNumberMsg = new JLabel("Card security number:");
	JLabel cardTypeMsg = new JLabel("Card Type:");
	JLabel purchaseAmountMsg = new JLabel("Purchase Amount:    $");
	JLabel expirationMsg = new JLabel("Expires:");
	JLabel slash = new JLabel("/");
	
	JTextField customerFirstName = new JTextField();
	JTextField customerLastName = new JTextField();
	JTextField nameOnCard = new JTextField();
	JTextField cardNumber = new JTextField();
	JTextField securityNumber = new JTextField();
	
	String[] cardTypeString = {"Visa", "Mastercard","American Express","Discover","Other"};
	JComboBox cardType = new JComboBox(cardTypeString);
	
	String[] expirationMonthString = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	JComboBox expirationMonth = new JComboBox(expirationMonthString);
	
	String[] expirationYearString = {"11","12","13","14","15","16","17","18","19","20"};
	JComboBox expirationYear = new JComboBox(expirationYearString);
	
	double amount;
	
	LinkedList<Items> list;
	
	public OrderWindow(double price, LinkedList<Items> tempList){
		amount = price;
		purchaseAmountMsg.setText(purchaseAmountMsg.getText() + amount);
		list = tempList;
		
		window.setSize(WindowWidth, WindowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		window.setResizable(false);
		
		customerFirstNameMsg.setSize(65,20);
		customerFirstNameMsg.setLocation(50,50);
		
		customerFirstName.setSize(200,20);
		customerFirstName.setLocation(120, 50);
		
		customerLastNameMsg.setSize(65,20);
		customerLastNameMsg.setLocation(360, 50);
		
		customerLastName.setSize(200,20);
		customerLastName.setLocation(430, 50);
		
		nameOnCardMsg.setSize(100,20);
		nameOnCardMsg.setLocation(50, 100);
		
		nameOnCard.setSize(400,20);
		nameOnCard.setLocation(150,100);
		
		cardNumberMsg.setSize(100,20);
		cardNumberMsg.setLocation(50,150);
		
		cardNumber.setSize(200,20);
		cardNumber.setLocation(150, 150);
		
		securityNumberMsg.setSize(140,20);
		securityNumberMsg.setLocation(400 ,150);
		
		securityNumber.setSize(30,20);
		securityNumber.setLocation(550,150);
		
		cardTypeMsg.setSize(100,20);
		cardTypeMsg.setLocation(50,200);
		
		cardType.setSize(110,20);
		cardType.setLocation(150,200);
		
		expirationMsg.setSize(100,20);
		expirationMsg.setLocation(280, 200);
		
		expirationMonth.setSize(50,20);
		expirationMonth.setLocation(350, 200);
		
		slash.setSize(20,20);
		slash.setLocation(407, 200);
		
		expirationYear.setSize(50,20);
		expirationYear.setLocation(420, 200);
		
		purchaseAmountMsg.setSize(300,20);
		purchaseAmountMsg.setLocation(50,250);
		
		order.setSize(100,40);
		order.setLocation(WindowWidth/2 +5, 300);
		order.setActionCommand("order");
		order.addActionListener(this);
		
		back.setSize(100,40);
		back.setLocation(WindowWidth/2 -105, 300);
		back.setActionCommand("back");
		back.addActionListener(this);
		
		window.add(customerFirstNameMsg);
		window.add(customerFirstName);
		window.add(customerLastNameMsg);
		window.add(customerLastName);
		window.add(nameOnCardMsg);
		window.add(nameOnCard);
		window.add(cardNumberMsg);
		window.add(cardNumber);
		window.add(securityNumberMsg);
		window.add(securityNumber);
		window.add(cardTypeMsg);
		window.add(cardType);
		window.add(expirationMsg);
		window.add(expirationMonth);
		window.add(slash);
		window.add(expirationYear);
		window.add(purchaseAmountMsg);
		window.add(order);
		window.add(back);
		
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "back"){
			ShoppingCart a = new ShoppingCart(amount, list);
			window.setVisible(false);
		}
		if(e.getActionCommand() == "order"){
			if(checkValidExpiration() == true){
				System.out.println("huzzah");
			}
			else{
				System.out.println("failure");
			}
		}
		
	}
	
	private boolean checkValidExpiration(){
		Calendar test = setupCalendar();
		return test.after(Calendar.getInstance());
	}
	
	private Calendar setupCalendar(){
		Calendar test = Calendar.getInstance();
		
		if(expirationYear.getSelectedItem() == "11"){
			test.set(2011, expirationMonth.getSelectedIndex(), 32);
		}
		else if(expirationYear.getSelectedItem() == "12"){
			test.set(2012, expirationMonth.getSelectedIndex(), 32);
		}
		else if(expirationYear.getSelectedItem() == "13"){
			test.set(2013, expirationMonth.getSelectedIndex(), 32);
		}
		else if(expirationYear.getSelectedItem() == "14"){
			test.set(2014, expirationMonth.getSelectedIndex(), 32);
		}
		else if(expirationYear.getSelectedItem() == "15"){
			test.set(2015, expirationMonth.getSelectedIndex(), 32);
		}
		else if(expirationYear.getSelectedItem() == "16"){
			test.set(2016, expirationMonth.getSelectedIndex(), 32);
		}
		else if(expirationYear.getSelectedItem() == "17"){
			test.set(2017, expirationMonth.getSelectedIndex(), 32);
		}
		else if(expirationYear.getSelectedItem() == "18"){
			test.set(2018, expirationMonth.getSelectedIndex(), 32);
		}
		else if(expirationYear.getSelectedItem() == "19"){
			test.set(2019, expirationMonth.getSelectedIndex(), 32);
		}
		else if(expirationYear.getSelectedItem() == "20"){
			test.set(2020, expirationMonth.getSelectedIndex(), 32);
		}
		return test;
	}

}
