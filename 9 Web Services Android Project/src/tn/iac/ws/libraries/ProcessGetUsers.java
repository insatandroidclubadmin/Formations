package tn.iac.ws.libraries;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import tn.iac.ws.UsersActivity;
import tn.iac.ws.adapter.MyCustomAdapter;
import tn.iac.ws.common.Config;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

/**
 * Async Task to get and send data to My Sql database through JSON respone.
 **/
public class ProcessGetUsers extends AsyncTask<String, String, JSONObject> {

	private ProgressDialog pDialog;
	private Context context;

	public ProcessGetUsers(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(context);
		pDialog.setTitle("Contacting Servers");
		pDialog.setMessage("Get Users ...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
	}

	@Override
	protected JSONObject doInBackground(String... args) {
		UserFunctions userFunction = new UserFunctions();
		JSONObject json = userFunction.getUsers();
		return json;
	}

	@Override
	protected void onPostExecute(JSONObject json) {
		try {
			if (json.getString(Config.KEY_SUCCESS) != null) {

				String res = json.getString(Config.KEY_SUCCESS);
				ArrayList<String> elements = new ArrayList<String>();

				if (Integer.parseInt(res) == 200) {

					JSONArray json_users = json.getJSONArray(Config.KEY_USERS);

					// looping through All Users
					for (int i = 0; i < json_users.length(); i++) {
						JSONObject user = json_users.getJSONObject(i);

						String username = user.getString(Config.KEY_USERNAME);

						elements.add(username);

					}

				}
				Intent i = new Intent(context, UsersActivity.class);
				i.putExtra(Config.KEY_USERS, elements);
				context.startActivity(i);
				pDialog.dismiss();
			}
		} catch (Exception e) {
			pDialog.dismiss();
			e.printStackTrace();
		}

	}
}