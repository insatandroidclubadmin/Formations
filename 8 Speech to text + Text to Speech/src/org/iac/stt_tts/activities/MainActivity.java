package org.iac.stt_tts.activities;

import java.util.ArrayList;
import java.util.Locale;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener,
		TextToSpeech.OnInitListener {

	private static final int RESULT_SPEECH = 1;
	private ImageButton buttonSTT;
	private Button buttonTTS;
	private TextView textViewEntry;
	private TextToSpeech tts;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Initialize graphic components
		buttonSTT = (ImageButton) findViewById(R.id.btnSpeak);
		buttonTTS = (Button) findViewById(R.id.buttonTTS);
		textViewEntry = (TextView) findViewById(R.id.textViewTextEntered);
		// button on click event
		buttonSTT.setOnClickListener(this);
		buttonTTS.setOnClickListener(this);

		tts = new TextToSpeech(this, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.speech_to_text, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSpeak:
			
			if (isConnectedToInternet()) {
				// Showing google speech input dialog
				  // create a RecognizerIntent
				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				
				// Simply takes user’s speech input and returns it to same activity
				// Use a language model based on free-form speech recognition.
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        		
				try {
					startActivityForResult(intent, RESULT_SPEECH);
				} catch (ActivityNotFoundException a) {
					Toast.makeText(
							getApplicationContext(),
							getResources().getString(
									R.string.speech_not_supported),
							Toast.LENGTH_SHORT).show();

				}
			} else {
				Toast.makeText(
						MainActivity.this,
						getResources().getString(
								R.string.internet_connection_unavailable),
						Toast.LENGTH_LONG).show();
			}

			break;

		case R.id.buttonTTS: {
			tts.speak(textViewEntry.getText().toString(),
					TextToSpeech.QUEUE_FLUSH, null);
		}
			break;
		default:

			break;
		}

	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown tts!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public void onInit(int status) {
		// Called after tts is initialized
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.getDefault());
			Log.v("Locale", "" + Locale.getDefault());

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.v("TTS", "This Language is not supported");
			} else {

				Log.v("TTS", "Ready to speak");
			}

		} else {
			Log.v("TTS", "Initilization Failed!");
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				textViewEntry.setText(text.get(0));
				textViewEntry.setVisibility(View.VISIBLE);
				buttonTTS.setVisibility(View.VISIBLE);
			}
			break;
		}

		}
	}

	private boolean isConnectedToInternet() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

}
