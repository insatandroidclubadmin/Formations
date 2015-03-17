package tn.iac.ws.libraries;

import org.json.JSONObject;

import tn.iac.ws.UsersActivity;
import tn.iac.ws.common.Config;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class ProcessRegister extends AsyncTask<String, String, JSONObject> {

	private ProgressDialog pDialog;
	private Context context;

	public ProcessRegister(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(context);
		pDialog.setTitle("Contacting Servers");
		pDialog.setMessage("Registering ...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
	}

	@Override
	protected JSONObject doInBackground(String... args) {
		String username = args[0];
		String email = args[1];
		String workshop = args[2];
		UserFunctions userFunction = new UserFunctions();
		JSONObject json = userFunction.registerUser(username, email, workshop);
		return json;

	}

	@Override
	protected void onPostExecute(JSONObject json) {
		/**
		 * Checks for success message.
		 **/
		try {
			if (json.getString(Config.KEY_SUCCESS) != null) {

				String res = json.getString(Config.KEY_SUCCESS);
				String red = json.getString(Config.KEY_ERROR);

				if (Integer.parseInt(res) == 200) {
					Toast.makeText(context, " Successfully Registered",
							Toast.LENGTH_SHORT).show();

					pDialog.dismiss();
					new ProcessGetUsers(context).execute();

				} else if (Integer.parseInt(red) == 400) {
					pDialog.dismiss();
					Toast.makeText(context,
							"JSON Error occured in Registartion",
							Toast.LENGTH_SHORT).show();
				} else if (Integer.parseInt(red) == 401) {
					pDialog.dismiss();
					Toast.makeText(context, "JSON ERROR", Toast.LENGTH_SHORT)
							.show();
				}

			} else {
				pDialog.dismiss();
				Toast.makeText(context, "Error occured in registration",
						Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}