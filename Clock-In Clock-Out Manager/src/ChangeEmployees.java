/*
 * By: Jerry Lu 
 * Course: ICS4U
 * Teacher: Mr. Benum
 * January 2019
 * 
 * ChangeEmployees Class
 * -a window to allow the user (employer) to add new employees or remove leaving employees
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChangeEmployees extends JFrame
							implements ActionListener
{
	//fields
	private JTextField newNameField, newWageField;
	private JButton addButton, removeButton;
	private JLabel addEmployee, nameHeader, wageHeader, removeEmployee, messageBox;
	private JComboBox selectEmp;
	
	//GridBagLayout GUI code obtained from Java textbook
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	//constructor
	/**
	 * constructs the UI
	 */
	public ChangeEmployees() 
	{	
		super("Change Employees");
		
		layout = new GridBagLayout();
		setLayout(layout);
		constraints = new GridBagConstraints();
	    
	    addEmployee = new JLabel("Add Employee:");
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(addEmployee, 0, 0, 2, 1); //the position and dimension of the component (row, column, width, height)
	    
	    nameHeader = new JLabel("New Employee's Name:   ");
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(nameHeader, 1, 0, 2, 1);
	    
	    //JTextField modeled from code in Benchmarks program
	    newNameField = new JTextField();
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(newNameField, 2, 0, 2, 1);
	    
	    wageHeader = new JLabel("Wage ($/Hr):");
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(wageHeader, 3, 0, 2, 1);
	    	
	    newWageField = new JTextField("input number only!");
	    newWageField.selectAll();
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(newWageField, 4, 0, 2, 1);
	    
	    addButton = new JButton("Add Employee");
	    addButton.addActionListener(this);
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(addButton, 5, 0, 1, 1);
	    
	    messageBox = new JLabel();
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(messageBox, 5, 1, 3, 1);
	    
	    removeEmployee = new JLabel("Remove Employee:");
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(removeEmployee, 0, 2, 2, 1);
	    
		//JComboBox modeled from code in Benchmarks program
	    selectEmp = new JComboBox(DataKeeper.getEmployeeNames()); //the drop down list contains all employee names
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(selectEmp, 1, 2, 2, 2);
	    
	    removeButton = new JButton("Remove Employee");
	    removeButton.addActionListener(this);
	    constraints.fill = GridBagConstraints.BOTH;
	    addComponent(removeButton, 3, 2, 2, 2);
	}
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton b = (JButton)e.getSource();
		
		if(b == addButton)
		{				
			String name = newNameField.getText().trim();
			
			//this section of code prevents the duplicate names in the program
			for(int i = 0; i<DataKeeper.getEmployeeNames().length; i++)
			{
				//compares the inputed name to current employee names. 
				if(name.equals(DataKeeper.getEmployeeNames()[i]))
				{
					messageBox.setText(name+ " already exists!");
					return;
				}
			}
			
			double wage;
			try
		      {
		        wage = Double.parseDouble(newWageField.getText().trim());
		      }
		      catch (NumberFormatException ex)
		      {
		        messageBox.setText("Enter a numerical value only!");
		        return;
		      }  
			DataKeeper.getEmployeeList().add(new Employee(name, wage)); //creates the new employee using obtained data
			DataKeeper.recordData(); //records the change
			selectEmp.addItem(name); //adds the employee to the drop-down list in the window
			messageBox.setText(name + " is added! Welcome!"); //confirm message
			
			newNameField.setText("");
			newWageField.setText("");
			
		} else if(b == removeButton)
		{
			int employee = selectEmp.getSelectedIndex();
			String n = (String) selectEmp.getSelectedItem();
			
			DataKeeper.getEmployeeList().remove(employee); //employee in the corresponding index is removed
			DataKeeper.recordData(); //record change
			selectEmp.removeItemAt(employee);//removes the employee from the drop-down list in the window
			messageBox.setText(n + " is removed. Goodbye!"); //confirm message
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
