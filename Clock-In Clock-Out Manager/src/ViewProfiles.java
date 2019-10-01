/*
 * By: Jerry Lu 
 * Course: ICS4U
 * Teacher: Mr. Benum
 * January 2019
 * 
 * ViewProfiles Class
 * -a window for the user (employer) to view shifts worked by his/her employees, and payroll for the past 2 months
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.*;

public class ViewProfiles extends JFrame	
						  implements ActionListener
{
	//fields
	private JComboBox selectEmp, selectMonth;
	private JButton view;
	private JTextArea info;
	private JScrollPane sp;
	
	//GridBagLayout GUI code obtained from Java textbook
	private GridBagLayout layout;
	private GridBagConstraints constraints;

	//constructor
	/**
	 * constructs the UI
	 */
	public ViewProfiles()
	{
		super("View Profiles");
		
		layout = new GridBagLayout();
		setLayout(layout);
		constraints = new GridBagConstraints();
	    
		//JComboBox modeled from code in Benchmarks program
	    selectEmp = new JComboBox(DataKeeper.getEmployeeNames()); //the drop down list contains all employee names
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(selectEmp, 0, 0, 1, 1); //the position and dimension of the component (row, column, width, height)
	    
	    selectMonth = new JComboBox(getMonthsList());
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(selectMonth, 1, 0, 1, 1);

	    view = new JButton("View");
	    view.addActionListener(this);
	    constraints.fill = GridBagConstraints.BOTH;
	    addComponent(view, 0, 1, 1, 2);

	    //code learned from Oracle website, tutorial on using JTextArea, https://docs.oracle.com/javase/tutorial/uiswing/components/textarea.html
	    info = new JTextArea(10, 1);
	    sp = new JScrollPane(info); //puts the text area inside a scroll pane so that the window won't be very long 
	    info.setEditable(false);
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    addComponent(sp, 2, 0, 2, 1);

		
	}
	
	//methods
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//the index of the selected employee in the JComboBox matches the index of the employee in DataKeeper's employeeList
		int employee = selectEmp.getSelectedIndex();
		int month = toMonthValue((String) selectMonth.getSelectedItem());		
		printInfo(employee, month);
	}
	
	/**
	 * -prints the employee's shifts worked and payroll for the requested month onto the JTextArea
	 * @param emp
	 */
	private void printInfo(int employee, int month)
	{
		DecimalFormat timeFormat = new DecimalFormat("#.####"); //to prevent repeating decimals
		DecimalFormat moneyFormat = new DecimalFormat("#.##"); //2 decimal places for currency
		double totalHours = 0.0;		
		
		//gets an ArrayList of shifts worked in the requested month by the requested employee
		ArrayList <Entry> entriesByMonth = DataKeeper.getEmployeeList().get(employee).getEntriesByMonth(month); 
		
		//displays employee's name and wage:
		info.setText("Name: " + DataKeeper.getEmployeeList().get(employee).getName() +
					"\nWage: $" + DataKeeper.getEmployeeList().get(employee).getWage() + "/Hr\n\n");
		info.append("M, D, In,        Out,   Total|\n"); //(month, day, time in, time out, duration)
		
		//prints each entry and calculates total hours worked for the month
		for (int i = 0; i<entriesByMonth.size(); i++)
		{
			info.append(entriesByMonth.get(i).toString() + "\n");
			totalHours += entriesByMonth.get(i).getDuration();
		}
		
		//displays total hours worked and money earned in the month
		info.append("\nTotal Hours: " + timeFormat.format(totalHours) + 
					"\nPayroll: $" + moneyFormat.format(totalHours*DataKeeper.getEmployeeList().get(employee).getWage()));
	}
	
	/**
	 * -helper method to provide the current month and previous month for user to select in JComboBox selectMonth
	 * @return an array of the 2 recent months
	 */
	private String[] getMonthsList()
	{
		int currentMonth = LocalDateTime.now().getMonthValue();
		int previousMonth = currentMonth -1;
		
		String[] monthsList = new String[2];
		int i = 0; //counter to update the array's index after each iteration of the for loop below
		
		//adds the 2 recent months onto the array monthsList
		for(int monthValue = previousMonth; monthValue<=currentMonth; monthValue++)
		{
			switch(monthValue)
			{
			case 0: monthsList[i] = "December"; //if currentMonth is January; the previous month should be December
				break;
			case 1: monthsList[i] = "January";
				break;
			case 2: monthsList[i] = "February";
				break;
			case 3: monthsList[i] = "March";
				break;
			case 4: monthsList[i] = "April";
				break;
			case 5: monthsList[i] = "May";
				break;
			case 6: monthsList[i] = "June";
				break;
			case 7: monthsList[i] = "July";
				break;
			case 8: monthsList[i] = "August";
				break;
			case 9: monthsList[i] = "September";
				break;
			case 10: monthsList[i] = "October";
				break;
			case 11: monthsList[i] = "November";
				break;
			case 12: monthsList[i] = "December";
				break;
			}		
			i++;
			
		}	
		return monthsList;
		
		
	}
	
	/**
	 * -helper method to convert the selected month in JComboBox monthsList into an integer value for printInfo method to use
	 * @param str
	 * @return the integer value of the month represented by str
	 */
	private int toMonthValue(String str)
	{
		switch(str)
		{
		case "January": return 1;
		case "February": return 2;
		case "March": return 3;
		case "April": return 4;
		case "May": return 5;
		case "June": return 6;
		case "July": return 7;
		case "August": return 8;
		case "September": return 9;
		case "October": return 10;
		case "November": return 11;
		case "December": return 12;
		default: return 0;
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
