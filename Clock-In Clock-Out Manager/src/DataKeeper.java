/*
 * By: Jerry Lu 
 * Course: ICS4U
 * Teacher: Mr. Benum
 * January 2019
 * 
 * DataKeeper Class
 * -a static class
 * -keeps a record of all employees and their corresponding info (name, wage, shifts) in EmployeeEntries.txt
 * -updates EmployeeEntries.txt whenever any info changes
 * -reads from EmployeeEntries.txt when program is launched to regain memory. No data is lost upon exiting program
 */

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class DataKeeper 
{
	//field
	private static ArrayList<Employee> employeeList = new ArrayList<Employee>(); //to contain all Employee objects and their associated info
	
	//public methods
	
	//the readData() and recordData() methods could be reused in other programs where saving data is necessary.
	
	/**
	 *-executed at launch of program.
	 *-reads info from EmployeeEntries.txt to employeeList to regain memory.
	 */
	public static void readData()
	{
		//code for reading from a text file using Scanner class obtained from ICS4U Moodle resources 
		//(https://moodle2.yrdsb.ca/mod/page/view.php?id=89664&inpopup=1)
		String pathname = "EmployeeEntries.txt";
		File file = new File(pathname);
		Scanner input = null;
		
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Cannot open file");
			System.exit(1);
		}
		
		//temporary variables to hold the info in the text file
		StringBuffer line;
		StringBuffer name;
		double wage;
		int month, day, inHour, inMinute, outHour, outMinute;
		double duration;
		int i = 0; //counter to ensure shifts are added to the last employee in the arraylist
		
		//this loop dissects each line of the text file and uses info contained in each line to construct an Employee object
		//outer loop creates the Employee object
		while(input.hasNextLine())
		{	
			//each line in the text file represents all of an Employee's info in the following format:
			//name, wage, [month, day, inHour:inMinute, outHour:outMinute, duration|, (subsequent entries in the same format)...]
			
			line = new StringBuffer(input.nextLine()); 
			
			name = new StringBuffer(line.substring(0, line.indexOf(",")));
			line.delete(0, line.indexOf(",")+2); //once a piece of info is obtained, it's deleted from the StringBuffer line (commas separate each info)
			
			wage = Double.parseDouble(line.substring(0, line.indexOf(","))); //the next piece of info conveniently starts at the first index
			line.delete(0, line.indexOf(",")+2);
			
			employeeList.add(new Employee(name.toString(), wage));
			
			//inner loop fills in all the shifts worked by that employee
			while (line.length()>2) //the StringBuffer line keeps getting smaller as obtained info is deleted from it
			{
				month = Integer.parseInt(line.substring(1, line.indexOf(",", 1)).trim());
				line.delete(0, line.indexOf(",", 1)+2);
				day = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line.delete(0, line.indexOf(",")+2);
				
				inHour = Integer.parseInt(line.substring(0, line.indexOf(":")));
				line.delete(0, line.indexOf(":")+1);
				inMinute = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line.delete(0, line.indexOf(",")+2);
				
				outHour = Integer.parseInt(line.substring(0, line.indexOf(":")));
				line.delete(0, line.indexOf(":")+1);
				outMinute = Integer.parseInt(line.substring(0, line.indexOf(",")));
				line.delete(0, line.indexOf(",")+2);
			
				duration = Double.parseDouble(line.substring(0, line.indexOf("|")));
				line.delete(0, line.indexOf("|")+1);
			
				employeeList.get(i).getShiftsList().add(new Entry(month, day, inHour, inMinute, outHour, outMinute, duration));
			}
			i++;
		}
		
	}
	
	/**
	 * -updates the data in EmployeeEntries.txt every time new info is added by rewriting the entire text file using the new info in employeeList.
	 */
	public static void recordData()
	{		
		//code for writing onto a text file using Scanner class obtained from ICS4U Moodle resources 
		//(https://moodle2.yrdsb.ca/mod/page/view.php?id=89666&inpopup=1)
		String pathname = "EmployeeEntries.txt";
		File file = new File(pathname);
		PrintWriter output = null;
		
		try
		{
			output = new PrintWriter (file);
			
		} catch (IOException ex)
		{
			
			System.out.println("Cannot create " + pathname);
			System.exit(1);
		}
				
		for(int i = 0; i<employeeList.size(); i++)
		{
			output.println(employeeList.get(i)); //the employee objects have overridden toString methods, which also call the entry objects' overridden toString	
			//keeps each employee's info on separate lines
		}
		
		output.close();
	}
	
	/**
	 * -executed at launch of program
	 * -saves memory by only keeping entries of the current and previous month 
	 */	
	public static void removeOldData()
	{
		int currentMonth = LocalDateTime.now().getMonthValue();
		int previousMonth = currentMonth -1;
		if(previousMonth == 0) 
		{
			//if the currentMonth is January, the previousMonth is December
			previousMonth = 12;
		}
		
		//traverses through the ArrayList of entries of each employee
		for(int i = 0; i<employeeList.size(); i++) //goes through each employee
		{
			for(int j = 0; j<employeeList.get(i).getShiftsList().size(); j++) //goes through each entry
			{
				if(employeeList.get(i).getShiftsList().get(j).getMonth() != currentMonth && 
					employeeList.get(i).getShiftsList().get(j).getMonth() != previousMonth) //the entry is recorded at least 2 months ago
				{
					employeeList.get(i).getShiftsList().remove(j);
					j--; //the counter is moved back as all subsequent entries are moved back by 1 index
				}
			}
		}
	}
	
	//accessor methods
	/**
	 * @return an array of employees' names 
	 * -for use in JComboBoxes to select employees
	 */
	public static String[] getEmployeeNames()
	{
		String[] names = new String[employeeList.size()];
		
		for (int i = 0; i<names.length; i++)
		{
			names[i] = employeeList.get(i).getName();
		}
		
		return names;
	}
	
	/**
	 * 
	 * @return the employeeList static variable
	 */
	public static ArrayList<Employee> getEmployeeList()
	{
		return employeeList;
	}
	
}
