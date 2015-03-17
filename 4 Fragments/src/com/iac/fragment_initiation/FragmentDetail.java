package com.iac.fragment_initiation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDetail extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment, container, false);
		return view;
	}
	
	public void setText(String e){
		TextView view = (TextView) getView().findViewById(R.id.detail);
		view.setText(e);
	}

}
