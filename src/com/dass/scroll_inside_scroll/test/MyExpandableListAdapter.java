package com.dass.scroll_inside_scroll.test;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.dass.scroll_inside_scroll.R;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
	private Context context;

	public MyExpandableListAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getGroupCount() {
		return 10;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public String getGroup(int groupPosition) {
		return "Group " + groupPosition;
	}

	@Override
	public ListAdapter getChild(int groupPosition, int childPosition) {
		return new ArrayAdapter<>(context, android.R.layout.simple_list_item_2,
				android.R.id.text2, new String[] { "Item 1", "Item 2",
						"Item 3", "Item 4", "Item 5", "Item 6", "Item 7",
						"Item 8", "Item 9", "Item 10" });
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return groupPosition + childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View v;
		TextView textView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(
					android.R.layout.simple_expandable_list_item_1, parent,
					false);
		} else
			v = (View) convertView;
		textView = (TextView) v.findViewById(android.R.id.text1);
		textView.setBackgroundColor(Color.DKGRAY);
		textView.setText(getGroup(groupPosition));
		return v;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		GridView gridView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gridView = (GridView) inflater.inflate(R.layout.child_content,
					parent, false);
			gridView.setAdapter(getChild(groupPosition, childPosition));
		} else
			gridView = (GridView) convertView;
		return gridView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
