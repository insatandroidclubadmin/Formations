package tn.insat.iac.adapter;

import java.util.ArrayList;

import tn.insat.iac.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyCustomAdapter extends ArrayAdapter<CBElement> implements OnClickListener{
	

	private ArrayList<CBElement> elements;
	private final Context context;
	
	public MyCustomAdapter(Context context){
		super(context,R.layout.element_layout);
		this.context = context;
		fill_list();
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		CBElement e = (CBElement) getItem(position);
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.element_layout, parent, false);
		}
		
		
		
		TextView text = (TextView) convertView.findViewById(R.id.element_text);
		ImageView image = (ImageView) convertView.findViewById(R.id.element_image);
		CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);
		
		text.setFocusable(false);
		image.setFocusable(false);
		
		text.setText(elements.get(position).getName());
		image.setBackgroundResource(elements.get(position).getImage());
		cb.setChecked(elements.get(position).isSelected());
		
		cb.setTag(e);
		cb.setOnClickListener(this);		
		
		return convertView;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return elements.size();
	}

	@Override
	public CBElement getItem(int position) {
		// TODO Auto-generated method stub
		return elements.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	public void refresh_element(){
		ArrayList<CBElement> newelements = new ArrayList<CBElement>();
		for(int i=0;i<elements.size();i++){
			if(elements.get(i).isSelected()){
				newelements.add(elements.get(i));
			}
		}
		elements = newelements;
		notifyDataSetChanged();
		
	}

	
	public void fill_list(){
		elements = new ArrayList<CBElement>();
		elements.add(new CBElement("Apple Pie", false, R.drawable.logo_iac));
		elements.add(new CBElement("Bananas Split", false, R.drawable.logo_iac));
		elements.add(new CBElement("Cupcake", false, R.drawable.logo_iac));
		elements.add(new CBElement("Donut", false, R.drawable.logo_iac));
		elements.add(new CBElement("Eclair", false, R.drawable.logo_iac));
		elements.add(new CBElement("Froyo", false, R.drawable.logo_iac));
		elements.add(new CBElement("Gingerbread", false, R.drawable.logo_iac));
		elements.add(new CBElement("Honeycomb", false, R.drawable.logo_iac));
		elements.add(new CBElement("Jellybean", false, R.drawable.logo_iac));
		elements.add(new CBElement("KitKat", false, R.drawable.logo_iac));
		notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		CBElement e = (CBElement)v.getTag();
		e.setSelected(((CheckBox)v).isChecked());
		Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show();
		
	}
}
