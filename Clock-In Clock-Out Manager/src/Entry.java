/*
 * By: Jerry Lu 
 * Course: ICS4U
 * Teacher: Mr. Benum
 * January 2019
 * 
 * Entry Class
 * -objects represent a single shift worked by an employee
 */

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Entry 
{
	//fields
	private int month, day; 
	private int inHour, inMinute; //starting time of shift
	private int outHour, outMinute; //ending time of shift
	private double duration; //in # of hours
	
	//constructors
	/**
	 * -used when an employee clocks in, thus ending time is unknown
	 * @param currentTime
	 */
	public Entry(LocalDateTime currentTime)
	{		
		month = currentTime.getMonthValue();
		day = currentTime.getDayOfMonth();
		inHour = currentTime.getHour();
		inMinute = currentTime.getMinute();
		outHour = 0;
		outMinute = 0;
		duration = 0.0;		
	}
	
	/**
	 * -used when DataKeeper is reading from EmployeeEntries.txt to regain memory. All info about the shift is already known.
	 * @param month
	 * @param day
	 * @param inHour
	 * @param inMinute
	 * @param outHour
	 * @param outMinute
	 * @param duration
	 */
	public Entry(int month, int day, int inHour, int inMinute, int outHour, int outMinute, double duration)
	{
		this.month = month;
		this.day = day;
		this.inHour = inHour;
		this.inMinute = inMinute;
		this.outHour = outHour;
		this.outMinute = outMinute;
		this.duration = duration;
	}
	
	//methods
	/**
	 * -adds the ending time of a shift and calculates the duration of the shift
	 * @param currentTime
	 */
	public void addEndTime(LocalDateTime currentTime)
	{	
			outHour = currentTime.getHour();	
			outMinute = currentTime.getMinute();
		
			int totalMinutes = (outHour-inHour)*60 + (outMinute-inMinute);
			duration = totalMinutes/60.0;	//duration is a value in terms of hours
	}
	
	/**
	 * -the overridden toString method to display info about the entry in EmployeeEntries.txt and in viewProfiles window
	 */
	public String toString()
	{
		DecimalFormat timeFormat =new DecimalFormat("#.####"); //to avoid repeating decimals
		
		String iH = inHour+"";
		String iM = inMinute+"";
		String oH = outHour+"";
		String oM = outMinute+"";
		
		//the  4 if statements below serve to standardize time display
		//i.e. changes times such as 6:1 to 06:01
		if(iH.length()==1)
		{
			iH = "0"+inHour;
		}
		if(iM.length()==1)
		{
			iM = "0"+inMinute;
		}
		if(oH.length()==1)
		{
			oH = "0"+outHour;
		}
		if(oM.length()==1)
		{
			oM = "0"+outMinute;
		}
		
		return month + ", " + day + ", " + 
			   iH + ":" + iM + ", " + 
			   oH + ":" + oM + ", " + 
			   timeFormat.format(duration) + "|";	
	}
	
	//Accessor methods
	/**
	 * @return the duration of a shift
	 */
	public double getDuration()
	{
		return duration;
	}
	
	/**
	 * @return the month when the shift was worked
	 */
	public int getMonth()
	{
		return month;
	}
}
