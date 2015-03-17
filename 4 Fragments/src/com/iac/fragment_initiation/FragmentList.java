package com.iac.fragment_initiation;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentList extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		String[] values = new String[] { "GL", "RT", "IIA", "IMI", "CH", "BIO",
				"CBA", "MPI" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, values);

		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String item = (String) getListAdapter().getItem(position);

		FragmentDetail fragment = (FragmentDetail) getFragmentManager()
				.findFragmentById(R.id.detail_fragment);

		if (fragment != null && fragment.isInLayout())
			fragment.setText(getDetail(item));
	}

	public String getDetail(String item) {
		if (item.equals("GL")) {
			return " Votre filière est : GL ";
		}
		if (item.equals("RT")) {
			return " Votre filière est : RT ";
		}
		if (item.equals("IMI")) {
			return " Votre filière est : IMI ";
		}
		if (item.equals("IIA")) {
			return " Votre filière est : IIA ";
		}
		if (item.equals("CH")) {
			return " Votre filière est : CH ";
		}
		if (item.equals("BIO")) {
			return " Votre filière est : BIO ";
		}
		if (item.equals("CBA")) {
			return " Votre filière est : CBA ";
		}
		if (item.equals("MPI")) {
			return " Votre filière est : MPI ";
		}

		return "!!";

	}

}
