package org.iac.iac_inscription.activities;

import java.util.List;

import org.iac.iac_inscription.db.MySQLiteHelper;
import org.iac.iac_inscription.models.Member;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ListMembersActivity extends ListActivity {

	MySQLiteHelper db = new MySQLiteHelper(this);
	Dialog dialog;
	LinearLayout l_layout;
	Member member;
	List<Member> list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// get all members
		list = db.getAllMembers();

		final String[] members_full_name = new String[list.size()];
		int i = 0;
		for (Member lis : list) {

			members_full_name[i] = lis.getFirstName() + " " + lis.getLastName();
			i++;
		}

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_members,
				members_full_name));

		ListView listView = getListView();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {

				member = db.getMember(arg2 + 1);

				dialog = new Dialog(ListMembersActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				l_layout = (LinearLayout) View.inflate(getApplicationContext(),
						R.layout.dialog_layout, null);
				dialog.setContentView(R.layout.dialog_layout);

				// detail
				Button b = (Button) dialog.findViewById(R.id.detail);
				b.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
						detail(v);
					}
				});

				// delete
				Button b2 = (Button) dialog.findViewById(R.id.delete);
				b2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
						supprimer(v);

					}
				});

				dialog.show();
			}
		});

	}

	@SuppressLint("NewApi") public void supprimer(View v) {
		db.deleteMember(member);
		this.recreate();

	}


	public void detail(View v) {
		Intent i = new Intent(getApplicationContext(), DetailActivity.class);
		i.putExtra("member", member);
		startActivity(i);

	}

}