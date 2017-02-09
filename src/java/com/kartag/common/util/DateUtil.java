package com.kartag.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
	private static long DATE_OFFSET = 15*1000*60;
	//private static long ONE_WEEK = 15*1000*60;
	public static java.util.Date getValidTripsTime()
	{
		java.util.Date newDate = new java.util.Date(System.currentTimeMillis() + DATE_OFFSET);
		return newDate;
	}
	
	public static java.util.Date getDateFromTimeAndDateStrings(String time, String date) throws Exception
	{
//		String[] timeArray = time.split(":");
//		String[] dateArray = date.split("-");
		Calendar calendar = Calendar.getInstance();
		if(date== null || "".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		java.util.Date formatedDate = formatter.parse(date + " " + time);
		calendar.setTime(formatedDate);
//		calendar.set(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]) - 1
//				, Integer.parseInt(dateArray[2]), Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
		return calendar.getTime();
	}
	/**
	 * return trip time in case weekly trip
	 * @param time
	 * @param date
	 * @return
	 */
	public static java.util.Date getDateFromTimeAndDateStringsForWeeklyTrip(String time, String date)
	{
		String[] timeArray = time.split(":");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
		Map<String, String> days = new HashMap<String, String>();
		days.put("Sun", "1");
		days.put("Mon", "2");
		days.put("Tue", "3");
		days.put("Wed", "4");
		days.put("Thu", "5");
		days.put("Fri", "6");
		days.put("Sat", "7");
		
		int selectedDay = Integer.parseInt(days.get(date));
		int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.set(Calendar.DAY_OF_WEEK, selectedDay);
		if(currentDay > selectedDay )
		{
		   calendar.add(Calendar.DATE, 7);
		}
		else if(currentDay == selectedDay )
		{
			int hour = Integer.parseInt(timeArray[0]);
			if(calendar.get(Calendar.HOUR_OF_DAY) >= hour)
			{
			   calendar.add(Calendar.DATE, 7);
			}
	    }
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	public static java.util.Date getDateFromTimeAndDateStringsPlusWeeks(java.util.Date  date, int weeks)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, weeks * 7);
		return calendar.getTime();
	}
	
	public static java.util.Date getDateFromString(String date) throws Exception
	{
		if(date== null || "".equals(date))
			return null;
		date +=" 00:00:00";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return  formatter.parse(date);
	}
	
	public static java.util.Date getStartSearchDate(String date) throws Exception
	{
		if(date== null || "".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatter.parse(date));
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return  calendar.getTime();
	}
	
	public static java.util.Date getEndSearchDate(String date) throws Exception
	{
		if(date== null || "".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatter.parse(date));
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		return  calendar.getTime();
	}
	
	public static java.util.Date getDateFromStringWithoutTime(String date) throws Exception
	{
		if(date== null || "".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return  formatter.parse(date);
	}
	
	public static java.util.Date now()
	{
		return new java.util.Date();
	}
	public static String getReportName()
	{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return "Report-" + formatter.format(new java.util.Date());
	}
	
	public static String getNowAsString()
	{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return formatter.format(new java.util.Date());
	}
	
	public static String getCurrentDateString()
	{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return  formatter.format(new java.util.Date());
	}
	public static java.util.Date getSimpleDateFromString(String date) throws Exception
	{
		if(date== null || "".equals(date))
			throw new IllegalArgumentException();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return  formatter.parse(date);
	}
//	public static Date getDateFromString(String date) throws Exception
//	{
//		if(date== null || "".equals(date))
//			return null;
//		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
//		return  new Date(formatter.parse(date).getTime());
//	}

}
