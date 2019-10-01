/*
 * By: Jerry Lu 
 * Course: ICS4U
 * Teacher: Mr. Benum
 * January 2019
 * 
 * ChangeWage Class
 * -a window to allow the user (employer) to adjust his/her employees' wages
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeWage extends JFrame
						implements ActionListener
{
	//fields
	private JComboBox selectEmp;
	private JTextField newWageField;
	private JLabel wageHeader, messageBox;
	private JButton confirmButton;
	
	//GridBagLayout GUI code obtained from Java textbook
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	//constructor
	/**
	 * constructs the UI
	 */
	public ChangeWage()
	{
		super("Change Wage");
		
		layout = new GridBagLayout();
		setLayout(layout);
		constraints = new GridBagConstraints();
	    
		//JComboBox modeled from code in Benchmarks program
	    selectEmp = new JComboBox(DataKeeper.getEmployeeNames()); //the drop down list contains all employee names
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(selectEmp, 0, 0, 2, 1); //the position and dimension of the component (row, column, width, height)
	    
		wageHeader = new JLabel("Wage ($/Hr):");
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(wageHeader, 1, 0, 1, 1);
	    
	    //JTextField modeled from code in Benchmarks program
    	newWageField = new JTextField("input number only!");
    	newWageField.selectAll();
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(newWageField, 1, 1, 1, 1);
    		
    	confirmButton = new JButton("Confirm");
    	confirmButton.addActionListener(this);
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(confirmButton, 2, 0, 2, 1);
    		
    	messageBox = new JLabel(".                                                                  ."); //large initial space to increase the length of the window
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(messageBox, 3, 0, 2, 1);
	    		
	}
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int employee = selectEmp.getSelectedIndex();
		double wage;
		
		try
		{
			wage = Double.parseDouble(newWageField.getText().trim());
			
		} catch (NumberFormatException ex)
		{	
			messageBox.setText("Enter a numerical value only!");
			return;
		}
		
		//the index of the selected employee in the JComboBox matches the index of the employee in DataKeeper's employeeList
		DataKeeper.getEmployeeList().get(employee).setWage(wage); //changes selected employee's wage
		DataKeeper.recordData(); //records the change
		
		String name = DataKeeper.getEmployeeList().get(employee).getName();
		messageBox.setText(name + "'s wage has been updated!"); //confirm message
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
