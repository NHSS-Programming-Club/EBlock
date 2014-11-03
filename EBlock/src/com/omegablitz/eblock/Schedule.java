package com.omegablitz.eblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Schedule extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);

		populateListView();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void populateListView() {
		Intent intent = getIntent();
		String[] myItems = intent.getStringArrayExtra(MainActivity.SCHEDULE);
		myItems[0] = "Monday:\t\t\t\t" + myItems[0];
		myItems[1] = "Tuesday:\t\t\t\t" + myItems[1];
		myItems[2] = "Wednesday:\t\t\t" + myItems[2];
		myItems[3] = "Thursday:\t\t\t" + myItems[3];
		myItems[4] = "Friday:\t\t\t\t\t" + myItems[4];

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myItems);
		ListView list = (ListView) findViewById(R.id.schedule);
		list.setAdapter(adapter);
	}
}
