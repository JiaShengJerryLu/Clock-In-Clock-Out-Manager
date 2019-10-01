/*
 * By: Jerry Lu 
 * Course: ICS4U
 * Teacher: Mr. Benum
 * January 2019
 * 
 * Employee Class
 * -objects represent an employee of a business
 */

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Employee 
{
	//fields
	private double wage;
	private String name;
	private ArrayList<Entry> shifts; //contains all the shifts worked by an employee in the past 2 months
	private boolean hasClockedIn, hasClockedOut; //used to prevent an employee from clocking in or out twice in a row

	//constructor
	/**
	 * @param name
	 * @param wage
	 */
	public Employee(String name, double wage)
	{
		this.name = name;
		this.wage = wage;
		shifts = new ArrayList<Entry>();	
		
	}
	
	//methods
	/**
	 * -marks the starting time of a shift
	 * -used by the ClockInOut window for an employee to clock in
	 */
	public void startShift()
	{
		shifts.add(new Entry(LocalDateTime.now())); //adds a new entry to the shifts list with the current time
		hasClockedIn = true;
		hasClockedOut = false;
	}
	
	/**
	 * -marks the ending time of a shift
	 * -used by the ClockInOut window for an employee to clock out
	 */
	public void endShift()
	{		
		shifts.get(shifts.size()-1).addEndTime(LocalDateTime.now()); //gives the ending time to the last entry of the shifts list, which was constructed at clock in
		hasClockedOut = true;
		hasClockedIn = false;
	}
	
	/**
	 * -changes the wage of an employee
	 * -used by the changeWage window
	 * @param newWage
	 */
	public void setWage(double newWage)
	{
		wage = newWage;
	}
	
	/**
	 * -used by the viewProfile window to view shifts worked in a particular month
	 * @param month
	 * @return an ArrayList of shifts worked in the requested month
	 * 
	 */
	public ArrayList<Entry> getEntriesByMonth(int month)
	{
		ArrayList<Entry> entriesByMonth= new ArrayList<Entry>();
		
		for(int i = 0; i<shifts.size(); i++)
		{
			if(shifts.get(i).getMonth() == month) //entries worked in the requested month are added onto a new AraryList
			{
				entriesByMonth.add(shifts.get(i));
			}			
		}
		
		return entriesByMonth;
	}
	
	/**
	 * -the overriden toString method to display an employee's info 
	 * -used by the DataKeeper class to record info into memory
	 */
	public String toString()
	{
		return name + ", " + wage + ", " + shifts.toString(); //calls each entry object's to string as well 
	}
	
	/**
	 * -determines whether or not the employee has already clocked in (to prevent an employee from clocking in twice in a row)
	 * 
	 * @return a boolean
	 */
	public boolean alreadyClockedIn()
	{		
		return hasClockedIn;
	}
	
	/**
	 * -determines whether or not the employee has already clocked out (to prevent an employee from clocking out twice in a row)
	 */
	public boolean alreadyClockedOut()
	{
		return hasClockedOut;
	}
	
	//accessor methods
	/**
	 * @return name of employee
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * @return wage of employee
	 */
	public double getWage()
	{
		return wage;
	}
	
	/**
	 * @return the ArrayList of shifts worked by the employee
	 */
	public ArrayList<Entry> getShiftsList()
	{
		return shifts;
	}
}
