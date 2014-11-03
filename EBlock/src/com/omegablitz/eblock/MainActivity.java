package com.omegablitz.eblock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
//import android.support.v7.app.ActionBarActivity;

public class MainActivity extends Activity {

	final public static String SCHEDULE = "com.omegablitz.eblock.SCHEDULE";
	public static final String SAVED_ID = "SavedID";
	EditText input;
	Button getSched;
	ProgressBar loading;

	//static Document doc = new Document(null);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		input = (EditText) findViewById(R.id.editText1);
		getSched = (Button) findViewById(R.id.button1);
		loading = (ProgressBar) findViewById(R.id.loading);
		
		SharedPreferences settings = getApplicationContext().getSharedPreferences(SAVED_ID, Context.MODE_PRIVATE);
		String id = settings.getString("id", "");
		input.setText(id);

		loading.setVisibility(View.INVISIBLE);
		getSched.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(v == getSched) {
					final String exec = input.getText().toString();
					new AsyncTaskRunner().execute(exec);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onStop() {
		loading.setVisibility(View.INVISIBLE);
		getSched.setVisibility(View.VISIBLE);

		SharedPreferences settings = getSharedPreferences(SAVED_ID, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("id", input.getText().toString());
		editor.commit();

		super.onStop();
	}
	private class AsyncTaskRunner extends AsyncTask<String, Void, String[]> {

		@Override
		protected String[] doInBackground(String... params) {

			try {
				return getRooms(params[0]);
			} catch (Exception e) {
				String[] fail = {"FAILED", "FAILED", "FAILED", "FAILED", "FAILED"};
				return fail;
			}
		}

		@Override
		protected void onPostExecute(String[] rooms) {
			Intent intent = new Intent(MainActivity.this, Schedule.class);
			intent.putExtra(SCHEDULE, rooms);
			startActivity(intent);
		}

		@Override
		protected void onPreExecute() {
			loading.setVisibility(View.VISIBLE);
			getSched.setVisibility(View.INVISIBLE);
		}
	}

	void debug(String text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	public static String[] getRooms(String id) throws Exception {
		String[] day = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		String[] rooms = new String[day.length];

		Document doc = Jsoup.connect("http://nsdsql.nashua.edu/events/eblocklookup/EblockSched.asp").data("street", id).post();
		Elements elements = doc.getElementById("TheTable").child(0).children();
		int num = 0;
		for(int i = 0; i < elements.size(); i++) {
			Element e = elements.get(i);
			if(e.children().size() == 0)
				continue;
			if(e.child(0).toString().contains(day[num])) {
				rooms[num] = e.child(0).siblingElements().get(2).toString().split("&nbsp;")[1].split("<")[0];
				num++;
			}
		}
		return rooms;
	}
}
