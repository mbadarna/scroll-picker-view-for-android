package com.ttshrk.view.scrollPicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.ttshrk.view.TimeScrollPickerView;

public class TimeSampleActivity extends Activity {
	TimeScrollPickerView timePickerView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	timePickerView = new TimeScrollPickerView(this);
    	timePickerView.setCurrentTime(false);
    	
    	ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, 300);
    	
    	setContentView(timePickerView, params);
   }
}