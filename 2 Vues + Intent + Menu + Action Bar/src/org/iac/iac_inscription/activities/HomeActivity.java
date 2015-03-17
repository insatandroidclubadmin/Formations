package org.iac.iac_inscription.activities;

import org.iac.iac_inscription.models.Member;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class HomeActivity extends ActionBarActivity {

	EditText nom;
	EditText prenom;
	Spinner atelier;
	Button valider;

	String val_nom;
	String val_prenom;
	String val_atelier;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		nom = (EditText) findViewById(R.id.nom);
		prenom = (EditText) findViewById(R.id.prenom);
		atelier = (Spinner) findViewById(R.id.ateliers);
		valider = (Button) findViewById(R.id.button_valider);

		// appliquer un listener à notre bouton valider

		valider.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				val_nom = nom.getText().toString();
				val_prenom = prenom.getText().toString();
				val_atelier = atelier.getSelectedItem().toString();

				if (val_nom.trim().equals(""))
					nom.setError(getResources().getString(R.string.error));
				else if(val_prenom.trim().equals(""))
					prenom.setError(getResources().getString(R.string.error));
				else {
					// construction d'une boite de dialog
					AlertDialog.Builder validerDialog = new AlertDialog.Builder(
							HomeActivity.this);
					validerDialog.setTitle(getResources().getString(
							R.string.dialog_title));
					// click sur le bouton annuler
					validerDialog.setNegativeButton(
							getResources().getString(R.string.annuler),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Toast.makeText(
											getApplicationContext(),
											getResources().getString(
													R.string.annuler),
											Toast.LENGTH_SHORT).show();
								}
							});

					// click sur le bouton confirmer

					validerDialog.setPositiveButton(
							getResources().getString(R.string.confirmer),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// Envoi de données à l'activité
									Member member = new Member(val_nom,
											val_prenom, val_atelier);
									Intent intent = new Intent(
											getApplicationContext(),
											InscriptionActivity.class);
									intent.putExtra("member", member);
									startActivity(intent);

								}
							}).show();

				}

			}
		}); // fin de listener

	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	switch (item.getItemId()) {
		case R.id.action_logo:
			Intent intent = new Intent(getApplicationContext(),
					LogoActivity.class);
			startActivity(intent);
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}

	}
}
