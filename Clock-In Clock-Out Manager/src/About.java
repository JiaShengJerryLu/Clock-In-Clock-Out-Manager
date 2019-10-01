/*
 * By: Jerry Lu 
 * Course: ICS4U
 * Teacher: Mr. Benum
 * January 2019
 * 
 * About Class
 * -a window listing information about this program and user's guide for each window
 */

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class About extends JFrame
{
	//field
	private JTextArea message; //contains info about the program
	
	//constructor
	public About()
	{
		super("About");
		
		//code for Container/GridLayout modeled from Benchmarks program
		Container c = getContentPane();
	    c.setLayout(new GridLayout(1, 1));
	    
	    //code learned from Oracle website, tutorial on using JTextArea, https://docs.oracle.com/javase/tutorial/uiswing/components/textarea.html
		message = new JTextArea(10, 2);
		message.setEditable(false);
		c.add(message);
		
		message.setText("Clock-In Clock-Out Manager Program\n"
						+"Made by Jerry Lu for ICS4U Culminating Project, January 2019\n"
						+"All rights reserved\n\n");
		
		message.append("Program Guide:\n\n");
		
		message.append("Clock-In Clock-Out Window:\n"
						+"-Select the name of the employee who is clocking in/out from the drop-down list.\n"
						+"-Click either the Clock In or Clock Out button accrodingly.\n "
						+"-A message below the buttons shall confirm the action has been done.\n\n");
		
		message.append("View Profiles Window:\n"
						+"-Select the name of the employee whose shifts/payroll you would like to view.\n"
						+"-Select the month for which you would like to see the selected employee's shifts/payroll.\n"
						+"-Click the View button to view the requested information.\n"
						+"The shifts are presented in the format 'Month, Day, Time In, Time Out, Total Hours Worked'.\n\n");
		
		message.append("Change Employees Window:\n"
						+"If you wish to add an employee:\n"
						+"-Input the employee's name in the first text field on the left hand side.\n"
						+"-Input the employee's current wage in the text field below.\n"
						+"(The input must be a numerical value only, such as '13.15', '15', etc.)\n"
						+"-Click the Add Employee button to add the employee to the program. A message shall confirm the action has been done.\n"
						+"If you wish to remove an employee:\n"
						+"-Select the employee's name in the drop down list on the right hand side.\n"
						+"-Click the Remove Employee button to remove the employee from the program. A message shall confirm the action has been done.\n\n");
		
		message.append("Change Wage Window:\n"
						+"-Select the name of the employee whose wage you would like to change in the drop-down list.\n"
						+"-Input the empoyee's new wage in the text field.\n"
						+"(The input must be a numerical value only, such as '13.15', '15', etc.)\n"
						+"-Click the Confirm button. A message shall confirm the action has been done.\n\n");
		
		message.append("About Window:\n"
						+"The current window you are in. As you can see, a guide to the program is provided here.");
		
	}
}
