package com.ttshrk.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 
 * @author ttshrk
 *
 */
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
		String[] separater = getResources().getStringArray(com.ttshrk.view.R.array.com_ttshrk_view_scroll_picker_time_separater);
		addSlot(getResources().getStringArray(com.ttshrk.view.R.array.com_ttshrk_view_scroll_picker_hour_list), 5, ScrollType.Ranged);
		addSlot(new String[]{separater[0]}, 2, ScrollType.None);
		addSlot(getResources().getStringArray(com.ttshrk.view.R.array.com_ttshrk_view_scroll_picker_minute_list), 5, ScrollType.Loop);
		if(separater.length > 1)
			addSlot(new String[]{separater[1]}, 2, ScrollType.None);
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
