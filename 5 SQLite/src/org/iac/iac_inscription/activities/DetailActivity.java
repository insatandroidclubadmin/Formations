package org.iac.iac_inscription.activities;

import org.iac.iac_inscription.db.MySQLiteHelper;
import org.iac.iac_inscription.models.Member;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {

	TextView member_id;
	TextView member_first_name;
	TextView member_last_name;
	TextView member_workshop;
	Member member ;

	MySQLiteHelper db ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			 member = (Member) extras.get("member");

		member_id = (TextView) findViewById(R.id.membre_id);
		member_first_name = (TextView) findViewById(R.id.membre_nom);
		member_last_name = (TextView) findViewById(R.id.membre_prenom);
		member_workshop = (TextView) findViewById(R.id.membre_atelier);
		
		member_id.setText(String.valueOf(member.getId()));
		member_first_name.setText(member.getFirstName());
		member_last_name.setText(member.getLastName());
		member_workshop.setText(member.getWorkShop());
		
		}

	}

}
