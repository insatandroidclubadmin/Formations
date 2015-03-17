package tn.iac.ws;

import tn.iac.ws.libraries.ProcessRegister;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private EditText username, email;
	private Spinner workshop;
	private Button submit;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initComponents();
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String usernameVal = username.getText().toString();
				String emailVal = email.getText().toString();
				String workshopVal = workshop.getSelectedItem().toString();
				new ProcessRegister(context).execute(usernameVal, emailVal,
						workshopVal);
			}
		});
	}

	private void initComponents() {
		username = (EditText) findViewById(R.id.username);
		email = (EditText) findViewById(R.id.email);
		workshop = (Spinner) findViewById(R.id.workshop);
		submit = (Button) findViewById(R.id.submit);
		context = MainActivity.this;
	}

}
