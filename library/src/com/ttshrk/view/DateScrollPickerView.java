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
		String[] separater = getResources().getStringArray(com.ttshrk.view.R.array.com_ttshrk_view_scroll_picker_ymd_separater);
		addSlot(getResources().getStringArray(com.ttshrk.view.R.array.com_ttshrk_view_scroll_picker_year_list), 5, ScrollType.Ranged);
		addSlot(new String[]{separater[0]}, 2, ScrollType.None);
		addSlot(getResources().getStringArray(com.ttshrk.view.R.array.com_ttshrk_view_scroll_picker_month_list), 5, ScrollType.Ranged);
		addSlot(new String[]{separater[1]}, 2, ScrollType.None);
		addSlot(getResources().getStringArray(com.ttshrk.view.R.array.scroll_picker_day_list), 5, ScrollType.Loop);
		if(separater.length > 2)
			addSlot(new String[]{separater[2]}, 2, ScrollType.None);
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
