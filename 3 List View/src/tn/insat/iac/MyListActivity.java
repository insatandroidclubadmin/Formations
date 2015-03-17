package tn.insat.iac;

import tn.insat.iac.adapter.MyCustomAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MyListActivity extends ListActivity implements OnItemClickListener {

	private MyCustomAdapter adapter;
	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_list);

		listview = getListView();
		listview.setOnItemClickListener(this);
		adapter = new MyCustomAdapter(this);
		setListAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_refresh:
			adapter.refresh_element();
			break;
		case R.id.action_fill:
			adapter.fill_list();
			break;

		}

		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, final View view,
			int position, long id) {

		Intent intent = new Intent(this, ContentActivity.class);
		TextView checked = (TextView) view.findViewById(R.id.element_text);
		String text = checked.getText().toString();
		intent.putExtra("checked", text);
		startActivity(intent);

		// view.setSelected(true);
	}

}