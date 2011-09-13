package com.ttshrk.datetimepicker.sample;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.ttshrk.view.TimeScrollPickerView;

public class CustomizeSampleActivity extends Activity {
	Handler handler = new Handler();
	CustomTimeScrollPickerView customView;
	Runnable timeUpdater = new Runnable() {
			@Override
			public void run() {
				customView.setCurrentTime(true);
				handler.postDelayed(timeUpdater, 500);
			}
		};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customView = new CustomTimeScrollPickerView(this);
        customView.setCurrentTime(true);
		
    	ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, 300);
		
		setContentView(customView, params);
		handler.postDelayed(timeUpdater, 500);
    }
}

class CustomTimeScrollPickerView extends TimeScrollPickerView {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH/mm/ss");
	protected int secondIndex;
	
	public CustomTimeScrollPickerView(Context context) {
		super(context);
	}
	
	@Override
	protected void init() {
		addSlot(new String[] { 
				"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", }, 5, ScrollType.Ranged);
		addSlot(new String[] { ":" }, 1, ScrollType.None);
		addSlot(new String[] { "00", "01", "02", "03", "04", "05", "06",
				"07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
				"27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
				"37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
				"47", "48", "49", "50", "51", "52", "53", "54", "55", "56",
				"57", "58", "59", }, 5, ScrollType.Loop);
		addSlot(new String[] { ":" }, 1, ScrollType.None);
		addSlot(new String[] { "00", "01", "02", "03", "04", "05", "06",
				"07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
				"27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
				"37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
				"47", "48", "49", "50", "51", "52", "53", "54", "55", "56",
				"57", "58", "59", }, 5, ScrollType.Loop);
		hourIndex = 0;
		minuteIndex = 2;
		secondIndex = 4;
	}
	
	/**
	 * 
	 */
	public void setCurrentTime(boolean isScroll) {
		String d = sdf.format(new Date());
		String[] ds = d.split("/");
		
		setHour(Integer.parseInt(ds[0]), isScroll);
		setMinute(Integer.parseInt(ds[1]), isScroll);
		setSecond(Integer.parseInt(ds[2]), isScroll);
	}
	

	/**
	 * 
	 * @param second
	 * @param isScroll
	 */
	public void setSecond(int second, boolean isScroll) {
		if(isScroll) {
			setSlotIndexByScroll(secondIndex, second);
		} else {
			setSlotIndex(secondIndex, second);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSecond() {
		return getSlotIndex(secondIndex);
	}
}