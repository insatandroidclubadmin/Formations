package tn.insat.iac;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ContentActivity extends Activity {

	private TextView textview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		
		textview = (TextView) findViewById(R.id.text);
		String text = getIntent().getExtras().getString("checked");
		textview.setText("You have selected : "+text);
		
		overridePendingTransition(R.anim.push_right_in ,R.anim.push_right_out );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.content, menu);
		return true;
	}

}
