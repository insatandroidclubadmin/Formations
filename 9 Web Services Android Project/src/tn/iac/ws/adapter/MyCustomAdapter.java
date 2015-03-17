package tn.iac.ws.adapter;

import java.util.ArrayList;

import tn.iac.ws.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyCustomAdapter extends ArrayAdapter<String> {

	private ArrayList<String> elements;
	private final Context context;

	public MyCustomAdapter(Context context, ArrayList<String> elements) {
		super(context, R.layout.element_layout);
		this.context = context;
		this.elements = elements;
		fill_list();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.element_layout, parent,
					false);
		}

		TextView text = (TextView) convertView.findViewById(R.id.element_text);
		text.setText(elements.get(position));

		return convertView;

	}

	@Override
	public int getCount() {
		return elements.size();
	}

	@Override
	public String getItem(int position) {
		return elements.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public void fill_list() {
		notifyDataSetChanged();
	}
}
