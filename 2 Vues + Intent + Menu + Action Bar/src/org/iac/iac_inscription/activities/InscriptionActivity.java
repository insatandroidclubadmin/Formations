package org.iac.iac_inscription.activities;

import org.iac.iac_inscription.models.Member;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class InscriptionActivity extends ActionBarActivity {
 TextView salutation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		salutation = (TextView) findViewById(R.id.salutation);
		
		
		Member friend = (Member) getIntent().getSerializableExtra("member");
		salutation.setText("Bonjour "+friend.getNom()+" "+friend.getPrenom()+"\nBienvenue dans l'atelier "+friend.getAtelier());
		
		
}
	
}
