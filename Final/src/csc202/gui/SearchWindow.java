package csc202.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

public class SearchWindow implements CaretListener, ActionListener{
	
	JTextField search = new JTextField();
	
	JFrame window = new JFrame("Search by Name");
	
	JButton back = new JButton("Back");
	JButton next = new JButton("Continue");
	
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);
	
	Object[] data = {"orange"};
	
	JScrollPane results = new JScrollPane(table);
	
	JCheckBox CBname = new JCheckBox();
	JCheckBox CBnumber = new JCheckBox();
	
	JLabel nameMsg = new JLabel("Search by name");
	JLabel numberMsg = new JLabel("Search by transaction number");
	
	public SearchWindow(JFrame frame){
		window = frame;
		window.getContentPane().removeAll();
		
		window.setSize(800,600);
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		search.setSize(350, 30);
		search.setLocation(50,50);
		search.addCaretListener(this);
		
		back.setSize(100,40);
		back.setLocation(50, 500);
		back.addActionListener(this);
		back.setActionCommand("back");
		
		next.setSize(100,40);
		next.setLocation(650, 500);
		next.addActionListener(this);
		next.setActionCommand("next");
		
		nameMsg.setSize(100, 20);
		nameMsg.setLocation(430, 50);
		
		numberMsg.setSize(180,20);
		numberMsg.setLocation(575, 50);
		
		CBname.setSize(20,20);
		CBname.setLocation(410, 50);
		CBname.addActionListener(this);
		CBname.setActionCommand("CBname");
		CBname.setSelected(true);
		
		CBnumber.setSize(20,20);
		CBnumber.setLocation(555, 50);
		CBnumber.addActionListener(this);
		CBnumber.setActionCommand("CBnumber");
		
		results.setSize(700, 350);
		results.setLocation(50, 100);
		
		Object columnName = "Search Results";
		
		model.addColumn(columnName);

		
		
		window.add(search);
		window.add(results);
		window.add(back);
		window.add(next);
		window.add(nameMsg);
		window.add(numberMsg);
		window.add(CBname);
		window.add(CBnumber);
		
		window.setLocationRelativeTo(null);
		
	}

	public void caretUpdate(CaretEvent a) {
		if(search.getText().compareTo("")==0){
			for(int i = model.getRowCount()-1; i >= 0; i--){
				model.removeRow(i);
			}
		}
		else{
			String temp = search.getText();
			if(CBname.isSelected() == true){
				//search function by name using temp string
			}
			if(CBnumber.isSelected() == true){
				//search function by number using temp string
			}
		}
		
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "back"){
			StartupWindow j = new StartupWindow(window);
		}
		if(e.getActionCommand() == "next"){
			
		}
		if(e.getActionCommand() == "CBname"){
			if(CBname.isSelected() == true){
				CBnumber.setSelected(false);
			}
			else{
				CBnumber.setSelected(true);
			}
		}
		if(e.getActionCommand() == "CBnumber"){
			if(CBname.isSelected() == true){
				CBname.setSelected(false);
			}
			else{
				CBname.setSelected(true);
			}
		}
		
	}

}
