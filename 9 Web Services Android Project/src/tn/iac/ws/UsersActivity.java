package tn.iac.ws;

import java.util.ArrayList;

import tn.iac.ws.adapter.MyCustomAdapter;
import tn.iac.ws.common.Config;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UsersActivity extends ListActivity implements OnItemClickListener {
	
	private MyCustomAdapter adapter;
	private ListView listView;
	private ArrayList<String> elements;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);
		elements = (ArrayList<String>) getIntent().getExtras().get(Config.KEY_USERS);
		listView = getListView();
		listView.setOnItemClickListener(this);
		adapter = new MyCustomAdapter(this, elements);
		setListAdapter(adapter);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(getApplicationContext(), elements.get(position), Toast.LENGTH_SHORT).show();
		
	}
}
