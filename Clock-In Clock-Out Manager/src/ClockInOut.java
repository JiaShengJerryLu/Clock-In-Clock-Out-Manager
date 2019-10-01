/*
 * By: Jerry Lu 
 * Course: ICS4U
 * Teacher: Mr. Benum
 * January 2019
 * 
 * ClockInOut Class
 * -a window for user (employee) to clock in or clock out on his/her shift
 */

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import javax.swing.*;

public class ClockInOut extends JFrame
						implements ActionListener 
{
	//fields
	private static JButton clockIn, clockOut;
	private static JLabel time;
	private JComboBox selectEmp; 
	
	//GridBagLayout GUI code obtained from Java textbook
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	//constructor
	/**
	 * constructs the UI
	 */
	public ClockInOut()
	{		
		super("Clock-In & Clock-Out");
		
		layout = new GridBagLayout();
		setLayout(layout);
		constraints = new GridBagConstraints();
	    
		//JComboBox modeled from code in Benchmarks program
	    selectEmp = new JComboBox(DataKeeper.getEmployeeNames());//the drop down list contains all employee names
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(selectEmp, 0, 0, 2, 1); //the position and dimension of the component (row, column, width, height)
    
	    clockIn = new JButton("Clock-In");
	    clockIn.addActionListener(this);
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(clockIn, 1, 0, 1, 1);
	    
	    clockOut= new JButton("Clock-Out");
	    clockOut.addActionListener(this);
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(clockOut, 1, 1, 1, 1);
	    
	    time =new JLabel(" ");
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(time, 2, 0, 2, 1);
	   
	}    
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();
	
		int employee = selectEmp.getSelectedIndex();
		
		if(b == clockIn)
		{
			//prevents an employee from clocking in twice in a row
			if(DataKeeper.getEmployeeList().get(employee).alreadyClockedIn())
			{
				time.setText("You've already clocked in!");		
				return;		
			}
			
			//the index of the selected employee in the JComboBox matches the index of the employee in DataKeeper's employeeList
			DataKeeper.getEmployeeList().get(employee).startShift(); //calls for that employee's startShift method
			DataKeeper.recordData(); //saves change in text file
			time.setText(LocalDateTime.now().toString() + "  SAVED"); //confirm message
			
		} else if(b == clockOut)
		{
			//prevents an employee from clocking out twice in a row
			if(DataKeeper.getEmployeeList().get(employee).alreadyClockedOut())
			{
				time.setText("You've already clocked out!");		
				return;		
			}
			
			DataKeeper.getEmployeeList().get(employee).endShift(); //calls for that employee's endShift method
			DataKeeper.recordData(); //saves change in text file
			time.setText(LocalDateTime.now().toString() + "  SAVED"); //confirm message
		}	
	}
	
	/**
	 * -code obtained from Java textbook
	 * -formats the components 
	 * @param comp
	 * @param row
	 * @param column
	 * @param width
	 * @param height
	 */
	private void addComponent(Component comp, int row, int column, int width, int height)
	{
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.insets = new Insets(3, 12, 3, 12); //spacing between each component (top, left, bottom, right)
		layout.setConstraints(comp, constraints);
		add(comp);
	}
	
}


