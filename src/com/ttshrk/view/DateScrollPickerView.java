package com.ttshrk.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;

public class DateScrollPickerView extends ScrollPickerView {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	protected int yearIndex;
	protected int monthIndex;
	protected int dayIndex;
	
	/**
	 * 
	 * @param context
	 */
	public DateScrollPickerView(Context context) {
		super(context);
		init();
	}
	
	public DateScrollPickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	protected void init() {

		addSlot(new String[] { 
				"1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", 
				"1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", 
				"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", 
				"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", 
				"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
				}, 5, ScrollType.Ranged);
		addSlot(new String[] { "年" }, 2, ScrollType.None);
		addSlot(new String[] { 
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				}, 5, ScrollType.Ranged);
		addSlot(new String[] { "月" }, 2, ScrollType.None);
		addSlot(new String[] { 
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31",
				}, 5, ScrollType.Loop);
		addSlot(new String[] { "日" }, 2, ScrollType.None);
		yearIndex = 0;
		monthIndex = 2;
		dayIndex = 4;
	}
	
	/**
	 * 
	 */
	public void setCurrentDate(boolean isScroll) {
		String d = sdf.format(new Date());
		String[] ds = d.split("/");
		
		setYear(Integer.parseInt(ds[0]), isScroll);
		setMonth(Integer.parseInt(ds[1]), isScroll);
		setDay(Integer.parseInt(ds[2]), isScroll);
	}
	
	/**
	 * 
	 * @param year
	 */
	public void setYear(int year, boolean isScroll) {
		if(isScroll) {
			setSlotIndexByScroll(yearIndex, year - 1970);
		} else {
			setSlotIndex(yearIndex, year - 1970);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getYear() {
		return getSlotIndex(yearIndex) + 1970;
	}
	
	/**
	 * 
	 * @param month
	 */
	public void setMonth(int month, boolean isScroll) {
		if(isScroll) {
			setSlotIndexByScroll(monthIndex, month - 1);
		} else {
			setSlotIndex(monthIndex, month - 1);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMonth() {
		return getSlotIndex(monthIndex) + 1;
	}

	/**
	 * 
	 * @param day
	 */
	public void setDay(int day, boolean isScroll) {
		if(isScroll) {
			setSlotIndexByScroll(dayIndex, day - 1);
		} else {
			setSlotIndex(dayIndex, day - 1);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDay() {
		return getSlotIndex(dayIndex) + 1;
	}
}
