/*
 * By: Jerry Lu 
 * Course: ICS4U
 * Teacher: Mr. Benum
 * January 2019
 * 
 * MainMenu Class
 * -a window that provides the buttons to construct all other windows
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame
					  implements ActionListener
{
	//fields
	private JButton clockInOut, changeEmployees, changeWage, viewProfiles, about; 
	
	//constructor
	/**
	 * constructs the UI
	 */
	public MainMenu()
	{
		super("Main Menu");
		
		//code for Container/GridLayout modeled from Benchmarks program
		Container c = getContentPane();
	    c.setLayout(new GridLayout(5, 1)); //5 rows and 1 column
	    
	    clockInOut = new JButton("Clock-In/Out");
	    clockInOut.addActionListener(this);
	    c.add(clockInOut);
	    
	    viewProfiles = new JButton("View Profiles");
	    viewProfiles.addActionListener(this);
	    c.add(viewProfiles);
	    
	    changeEmployees = new JButton("Change Employees");
	    changeEmployees.addActionListener(this);
	    c.add(changeEmployees);
	    
	    changeWage = new JButton("Change Wage");
	    changeWage.addActionListener(this);
	    c.add(changeWage);
	    
	    about = new JButton("About");
	    about.addActionListener(this);
	    c.add(about);	    
	}
	
	//method
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton b = (JButton)e.getSource();
		
		//loads the corresponding window according to the button clicked
		if(b == clockInOut)
		{
			ClockInOut window = new ClockInOut();
			window.setBounds(50, 500, 500, 500); //just for the position of the window. Dimensions are set by pack method
			window.pack(); //removes any empty space on the outside
			window.setResizable(false);
		    window.setVisible(true);
		    
		} else if(b == changeEmployees) 
		{
			ChangeEmployees window = new ChangeEmployees();
			window.setBounds(50, 500, 500, 500);
			window.pack();
			window.setResizable(false);
		    window.setVisible(true);
		    
		} else if (b == viewProfiles)
		{
			ViewProfiles window = new ViewProfiles();
			window.setBounds(50, 500, 500, 500);
			window.pack();
			window.setResizable(false);
		    window.setVisible(true);
		    
		} else if (b == changeWage)
		{
			ChangeWage window = new ChangeWage();
			window.setBounds(50, 500, 500, 500);
			window.pack();
			window.setResizable(false);
		    window.setVisible(true);
		    
		} else if (b == about)
		{
			About window = new About();
			window.setBounds(50, 500, 500, 500);
			window.pack();
			window.setResizable(false);
		    window.setVisible(true);   
		}				
	}

	public static void main(String[] args)
	{
		//upon program launch, regain memory and remove any old data (more than 2 months ago)
		DataKeeper.readData();
		DataKeeper.removeOldData();
		
		MainMenu window = new MainMenu();
		window.setBounds(50, 50, 200, 300);
	    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    window.setVisible(true);
	      
	}
}
