package com.omegablitz.eblock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
		String[] rooms = intent.getStringArrayExtra(MainActivity.SCHEDULE);
		String[] teachers = intent.getStringArrayExtra(MainActivity.TEACHERS);
		String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		

		TableLayout table = new TableLayout(this);
		TableRow.LayoutParams cellParam = new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1.0f);
		TableLayout.LayoutParams rowParam = new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1.0f);
		for (int r = 0; r < 5; ++r)
		{
		    TableRow row = new TableRow(this);
		    int fontSize = 17;
		    
		    TextView day = new TextView(this);
		    day.setTypeface(Typeface.SERIF, Typeface.BOLD);
		    day.setTextSize(fontSize);
		    day.setText(days[r]);
		    day.setGravity(Gravity.CENTER);
		    row.addView(day, cellParam);
		    
		    TextView teacher = new TextView(this);
		    teacher.setTypeface(Typeface.SERIF, Typeface.BOLD);
		    teacher.setTextSize(fontSize);
		    teacher.setText(teachers[r]);
		    teacher.setGravity(Gravity.CENTER);
		    row.addView(teacher, cellParam);
		    
		    TextView room = new TextView(this);
		    room.setTypeface(Typeface.SERIF, Typeface.BOLD);
		    room.setTextSize(fontSize);
		    room.setText(rooms[r]);
		    room.setGravity(Gravity.CENTER);
		    row.addView(room, cellParam);
		    
		    table.addView(row, rowParam);
		    if(r % 2 != 0)
		    	row.setBackgroundColor(Color.parseColor("#A76EBF"));
		    else
		    	row.setBackgroundColor(Color.parseColor("#933BB8"));
		}
		setContentView(table);
	}
}
