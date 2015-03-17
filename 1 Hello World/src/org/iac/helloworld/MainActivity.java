package org.iac.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{
 
	Button buttonClickMe ;
	EditText editTextName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClickMe =  (Button) findViewById(R.id.buttonClickMe);
        editTextName =  (EditText) findViewById(R.id.editTextName);
        buttonClickMe.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {
      				Toast.makeText(getApplicationContext(),editTextName.getText().toString(),Toast.LENGTH_LONG).show();
      				
			}
		});
        
    }


    
}
