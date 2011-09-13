package com.ttshrk.datetimepicker.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.ttshrk.view.ScrollPickerView;

public class ListSampleActivity extends Activity {
	ScrollPickerView scrollPickerView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scrollPickerView = new ScrollPickerView(this);
        scrollPickerView.addSlot(getResources().getStringArray(R.array.custom_list), 1, ScrollPickerView.ScrollType.Ranged);
        scrollPickerView.setSlotIndex(0, 13);
    	ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, 200);

		this.setContentView(scrollPickerView, params);
    }
}