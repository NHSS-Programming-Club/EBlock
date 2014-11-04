package com.omegablitz.eblock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
		TableRow.LayoutParams width = new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1.0f);
		for (int r = 0; r < 5; ++r)
		{
		    TableRow row = new TableRow(this);
		    
		    TextView day = new TextView(this);
		    day.setTypeface(Typeface.SANS_SERIF);
		    day.setTextSize(15);
		    day.setText(days[r]);
		    row.addView(day, width);
		    
		    TextView teacher = new TextView(this);
		    teacher.setTypeface(Typeface.SANS_SERIF);
		    teacher.setTextSize(15);
		    teacher.setText(teachers[r]);
		    row.addView(teacher, width);
		    
		    TextView room = new TextView(this);
		    room.setTypeface(Typeface.SANS_SERIF);
		    room.setTextSize(15);
		    room.setText(rooms[r]);
		    row.addView(room, width);
		    
		    table.addView(row);
		}
		setContentView(table);
	}
}
