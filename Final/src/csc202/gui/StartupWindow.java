package csc202.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class StartupWindow implements ActionListener {
	//Dimensions of the window
	int WindowHeight = 250;
	int WindowWidth = 300;
	
	//Dimensions of the buttons
	int ButtonHeight = 50;
	int ButtonWidth = 200;
	
	//variables for the center of the window
	int CenterX = WindowWidth/2;
	int CenterY = WindowHeight/2;
	
	//variables used to center the buttons
	int CenterButtonX = CenterX - ButtonWidth/2;
	int CenterButtonY = CenterY - ButtonHeight/2;
	
	//creates the window
	JFrame window = new JFrame();
	
	//creates the buttons
	JButton order = new JButton("Place order");
	JButton returnOrder = new JButton("Return Order");
	
	
	public StartupWindow(JFrame frame){
		
		window = frame;
		window.getContentPane().removeAll();
		
		//setup the JFrame
		window.setSize(WindowWidth, WindowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		
		//setup the order button
		order.setSize(ButtonWidth, ButtonHeight);
		order.setLocation(CenterButtonX, CenterButtonY -(ButtonHeight / 2) - 5);
		order.setActionCommand("order");
		order.addActionListener(this);
		
		//setup the return order button
		returnOrder.setSize(ButtonWidth, ButtonHeight);
		returnOrder.setLocation(CenterButtonX, CenterButtonY + (ButtonHeight / 2) + 5);
		returnOrder.setActionCommand("return");
		returnOrder.addActionListener(this);
		
		//add to the JFrame
		window.add(order);
		window.add(returnOrder);
		
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		//Order button handler
		if(e.getActionCommand() == "order"){
			ShoppingCart j = new ShoppingCart(window, 0.0, null);
		}
		
		//return button handler
		else if(e.getActionCommand() == "return"){
			SearchWindow c = new SearchWindow(window);
		}
		
		
	}

}
