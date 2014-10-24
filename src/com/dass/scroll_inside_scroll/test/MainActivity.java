package com.dass.scroll_inside_scroll.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.dass.scroll_inside_scroll.R;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListInScroll();
		// setScrollInScroll();
	}

	private void setScrollInScroll() {
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

	private void setListInScroll() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.list_in_scroll, null);
		ListView listView = (ListView) v.findViewById(R.id.listView);
		listView.setAdapter(new ArrayAdapter<>(getApplicationContext(),
				android.R.layout.simple_list_item_2, android.R.id.text2,
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4",
						"Item 5", "Item 6", "Item 7", "Item 8", "Item 9",
						"Item 10" }));
		setContentView(v);
	}

}
