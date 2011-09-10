package com.ttshrk.view.scrollPicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ttshrk.view.DateScrollPickerView;

public class DateSampleActivity extends Activity {
	DateScrollPickerView datePickerView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datesample);

		datePickerView = (DateScrollPickerView) findViewById(R.id.picker1);
		datePickerView.setCurrentDate(true);

		final EditText editText1 = (EditText) findViewById(R.id.editText1);
		final EditText editText2 = (EditText) findViewById(R.id.editText2);
		final EditText editText3 = (EditText) findViewById(R.id.editText3);

		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editText1.setText(""+datePickerView.getYear());
				editText2.setText(""+datePickerView.getMonth());
				editText3.setText(""+datePickerView.getDay());
			}
		});

		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				datePickerView.setYear(toInt(editText1.getText().toString()), true);
				datePickerView.setMonth(toInt(editText2.getText().toString()), true);
				datePickerView.setDay(toInt(editText3.getText().toString()), true);
			}
		});
	}

	
	int toInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			return 0;
		}
	}
}