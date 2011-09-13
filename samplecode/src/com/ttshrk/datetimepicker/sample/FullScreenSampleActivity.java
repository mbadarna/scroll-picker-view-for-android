package com.ttshrk.datetimepicker.sample;

import android.app.Activity;
import android.os.Bundle;

import com.ttshrk.view.TimeScrollPickerView;

public class FullScreenSampleActivity extends Activity {
	TimeScrollPickerView timePickerView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timePickerView = new TimeScrollPickerView(this);
        timePickerView.setCurrentTime(true);
		
		setContentView(timePickerView);
    }
}