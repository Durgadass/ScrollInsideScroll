package com.dass.scroll_inside_scroll.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.dass.messaging1.R;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.scroll_in_scroll, null);
		ListView listView = (ListView) v.findViewById(R.id.listView1);
		listView.setAdapter(new ArrayAdapter<>(getApplicationContext(),
				android.R.layout.simple_list_item_2, android.R.id.text2,
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4",
						"Item 5", "Item 6", "Item 7", "Item 8", "Item 9",
						"Item 10" }));
		ExpandableListView expandableListView = (ExpandableListView) v
				.findViewById(R.id.expandableListView1);
		expandableListView.setAdapter(new MyExpandableListAdapter(
				getApplicationContext()));
		setContentView(v);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
