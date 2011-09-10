package com.ttshrk.view.scrollPicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SampleActivity extends Activity implements
		AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
	public static final String INTENT_KEY = "MENU_POSITION";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		
		for (String m : getResources().getStringArray(R.array.list_menus)) {
			adapter.add(m);
		}

		ListView listView = new ListView(this.getApplicationContext());
		listView.setAdapter(adapter);
		setContentView(listView);

		// item clicked callback
		listView.setOnItemClickListener(this);
		// item selected callback
		listView.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = null;
		switch(position) {
		case 0:
			intent = new Intent(this, TimeSampleActivity.class);
			break;
		case 1:
			intent = new Intent(this, DateSampleActivity.class);
			break;
		case 2:
			intent = new Intent(this, ListSampleActivity.class);
			break;
		case 3:
			intent = new Intent(this, FullScreenSampleActivity.class);
			break;
		case 4:
			intent = new Intent(this, CustomizeSampleActivity.class);
			break;
		}
		startActivity(intent);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		ListView listView = (ListView) parent;
		Log.i("onItemSelected", (String) listView.getItemAtPosition(position));
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}