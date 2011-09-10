package com.ttshrk.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;

public class TimeScrollPickerView extends ScrollPickerView {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH/mm");
	protected int hourIndex;
	protected int minuteIndex;

	public TimeScrollPickerView(Context context) {
		super(context);
		init();
	}
	
	public TimeScrollPickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	/**
	 * 
	 * @param context
	 */
	protected void init() {
		addSlot(new String[] { 
				"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", }, 5, ScrollType.Ranged);
		addSlot(new String[] { "時" }, 2, ScrollType.None);
		addSlot(new String[] { "00", "01", "02", "03", "04", "05", "06",
				"07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
				"27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
				"37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
				"47", "48", "49", "50", "51", "52", "53", "54", "55", "56",
				"57", "58", "59", }, 5, ScrollType.Loop);
		addSlot(new String[] { "分" }, 2, ScrollType.None);
		hourIndex = 0;
		minuteIndex = 2;
	}
	
	/**
	 * 
	 */
	public void setCurrentTime(boolean isScroll) {
		String d = sdf.format(new Date());
		String[] ds = d.split("/");
		
		setHour(Integer.parseInt(ds[0]), isScroll);
		setMinute(Integer.parseInt(ds[1]), isScroll);
	}

	/**
	 * 
	 * @param hour
	 * @param isScroll
	 */
	public void setHour(int hour, boolean isScroll) {
		if(isScroll) {
			setSlotIndexByScroll(hourIndex, hour);
		} else {
			setSlotIndex(hourIndex, hour);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHour() {
		return getSlotIndex(hourIndex);
	}

	/**
	 * 
	 * @param minute
	 * @param isScroll
	 */
	public void setMinute(int minute, boolean isScroll) {
		if(isScroll) {
			setSlotIndexByScroll(minuteIndex, minute);
		} else {
			setSlotIndex(minuteIndex, minute);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMinute() {
		return getSlotIndex(minuteIndex);
	}
}
