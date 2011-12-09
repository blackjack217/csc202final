package csc202.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;

import csc202.transaction.Items;


public class ShoppingCart implements ActionListener{

	int WindowWidth = 400;
	int WindowHeight = 350;
	
	double amount = 0;
	
	String amountString;
	
	JFrame window = new JFrame("Shopping Cart");
	
	JButton addItem = new JButton("Add a new item");
	JButton removeItem = new JButton("Remove an item");
	JButton back = new JButton("Back");
	JButton next = new JButton("Continue");
	
	JLabel total = new JLabel("Total:   $");
	JLabel amountMsg = new JLabel();
	
	JTextArea order = new JTextArea();
	JScrollPane orderScroll = new JScrollPane(order);
	
	LinkedList<Items> itemList = new LinkedList<Items>();
	
	//sets up the main window
	public ShoppingCart(Double price, LinkedList<Items> tempList){
		amount = price;
		amountMsg.setText(Double.toString(amount));
		
		if(tempList != null){
			itemList = tempList;
		}
		
		window.setSize(WindowWidth, WindowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		
		addItem.setSize(125,30);
		addItem.setLocation(50,50);
		addItem.setActionCommand("addItem");
		addItem.addActionListener(this);
		
		removeItem.setSize(125,30);
		removeItem.setLocation(WindowWidth - 175, 50);
		removeItem.setActionCommand("remove");
		removeItem.addActionListener(this);
		
		back.setSize(100,30);
		back.setLocation(50, 250);
		back.setActionCommand("back");
		back.addActionListener(this);
		
		next.setSize(100,30);
		next.setLocation(WindowWidth - 150, 250);
		next.setActionCommand("next");
		next.addActionListener(this);
		
		total.setSize(50,20);
		total.setLocation(50 ,100);
		
		amountMsg.setSize(WindowWidth/2, 20);
		amountMsg.setLocation(100, 100);
		
		orderScroll.setSize(300,100);
		orderScroll.setLocation(50, 120);
		//order.setEditable(false);
		order.setLineWrap(true);
		order.setEditable(false);
		
		window.add(addItem);
		window.add(removeItem);
		window.add(back);
		window.add(next);
		window.add(total);
		window.add(amountMsg);
		window.add(orderScroll);
		
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		//controls the continue button
		if(e.getActionCommand() == "next"){
			OrderWindow a = new OrderWindow(amount , itemList);
			window.setVisible(false);
		}
		//controls the back button
		if(e.getActionCommand() == "back"){
			StartupWindow b = new StartupWindow();
			window.setVisible(false);
		}
		//controls the add item button
		if(e.getActionCommand() == "addItem"){
			addItem();
		}
		//controls the remove item button
		if(e.getActionCommand() == "remove"){
			removeItem();
		}
		
		
		//Commands involving the pop-up windows
		//closes the pop-up windows
		if(e.getActionCommand() == "close"){
			addWindow.setVisible(false);
			removeWindow.setVisible(false);
		}
		//controls the add item button in the pop-up window
		if(e.getActionCommand() == "add"){
			try{
				Double temp = Double.parseDouble(amountTextBox.getText());
				temp = standardizeCost(temp);
				Items tempItem = new Items(itemName.getText(),temp, Integer.parseInt(quantity.getText()));
				itemList.add(tempItem);
				amount = amount + tempItem.getTotalPrice();
				amountMsg.setText(Double.toString(amount));
				order.setText(order.getText() + tempItem + "\n");
				addWindow.setVisible(false);
				
				itemName.setText("");
				amountTextBox.setText("");
				quantity.setText("");
			}
			catch(NumberFormatException q){
				System.out.println(q);
			} catch (Exception a) {
				
			}
		}
		//controls the remove item button in the pop-up window
		if(e.getActionCommand() == "remove item"){
			try{
				Items tempItem = itemList.get(orderList.getSelectedIndex());
				Double temp = tempItem.getTotalPrice();
				amount = amount - temp;
				itemList.remove(orderList.getSelectedIndex());
				amountMsg.setText(Double.toString(amount));
				removeLine(tempItem.toString());
				removeWindow.setVisible(false);
			}
			catch(IndexOutOfBoundsException r){
				removeWindow.setVisible(false);
			}
		}
		
	}
	
	//Button for the add item window and the remove item window
	JButton close = new JButton("Close");
	
	//Button for the add item window
	JButton addItemTemp = new JButton("Add");
	
	JFrame addWindow = new JFrame();
	JFrame removeWindow = new JFrame();
	
	JTextField amountTextBox = new JTextField();
	JTextField itemName = new JTextField();
	JTextField quantity = new JTextField();
	
	//sets up the window to add items
	private void addItem(){
		if(addWindow.getTitle() == "Add Item"){
			
			addWindow.setVisible(true);
		}
		else{
			addWindow.setTitle("Add Item");
			addWindow.setSize(250,335);
			addWindow.setLayout(null);
		
			addItemTemp.setSize(70,25);
			addItemTemp.setLocation(145, 255);
			addItemTemp.setActionCommand("add");
			addItemTemp.addActionListener(this);
		
			close.setSize(70,25);
			close.setLocation(35, 255);
			close.setActionCommand("close");
			close.addActionListener(this);
			
			JLabel itemNameMsg = new JLabel("What is this item called?");
			
			itemNameMsg.setSize(150,20);
			itemNameMsg.setLocation(35,35);
			
			itemName.setSize(180,30);
			itemName.setLocation(35, 60);
		
			JLabel amountMsg = new JLabel("How much is the item?");
		
			amountMsg.setSize(150,20);
			amountMsg.setLocation(35,100);
		
			amountTextBox.setSize(180,30);
			amountTextBox.setLocation(35, 130);
			
			JLabel quantityMsg = new JLabel("How many units?");
			
			quantityMsg.setSize(150,20);
			quantityMsg.setLocation(35, 170);
			
			quantity.setSize(180,30);
			quantity.setLocation(35, 200);
		
			addWindow.add(addItemTemp);
			addWindow.add(close);
			addWindow.add(amountMsg);
			addWindow.add(amountTextBox);
			addWindow.add(itemName);
			addWindow.add(quantity);
			addWindow.add(itemNameMsg);
			addWindow.add(quantityMsg);
		
			addWindow.setLocationRelativeTo(null);
			addWindow.setResizable(false);
			addWindow.setVisible(true);
		}
	}
	
	JButton remove = new JButton("Remove");
	JComboBox orderList = new JComboBox();
	
	//sets up the window to remove items
	private void removeItem(){
		if(removeWindow.getTitle() == "Remove Item"){
			setupComboBox();
			removeWindow.setVisible(true);
		}
		else{
			removeWindow.setTitle("Remove Item");
			removeWindow.setSize(250,190);
			removeWindow.setLayout(null);
			
			JLabel removeMsg = new JLabel("Remove which item?");
			
			removeMsg.setSize(150,20);
			removeMsg.setLocation(35,35);
			
			remove.setSize(90,25);
			remove.setLocation(115,100);
			remove.addActionListener(this);
			remove.setActionCommand("remove item");
			
			close.setSize(70,25);
			close.setLocation(35, 100);
			close.setActionCommand("close");
			close.addActionListener(this);
			
			setupComboBox();
			orderList.setSize(170,30);
			orderList.setLocation(35, 60);
			
			removeWindow.add(removeMsg);
			removeWindow.add(remove);
			removeWindow.add(close);
			removeWindow.add(orderList);
			
			removeWindow.setLocationRelativeTo(null);
			removeWindow.setResizable(false);
			removeWindow.setVisible(true);
			//String.format("%.2d", nameOfDouble);
		}
	}
	
	//sets up the combo box for the items entered
	private void setupComboBox(){
		if(itemList.isEmpty() != true){
			orderList.removeAllItems();
			for(int i=0; i<itemList.size(); i++){
				orderList.addItem(itemList.get(i));
			}
		}
	}
	
	private void removeLine(String line){
		String tempOrder = order.getText();
		line = line+"\n";
		if(tempOrder.contains(line) == true){
			tempOrder = tempOrder.replace(line, "");
			order.setText(tempOrder);
		}
	}
	
	private double standardizeCost(Double cost){
		if(cost%.01 >= .005){
			cost = cost + .01;
		}
		cost = cost - (cost % .01);
		return cost;
	}
}
