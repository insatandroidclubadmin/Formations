package tn.iac.ws.libraries;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import tn.iac.ws.common.Config;

public class UserFunctions {

	private JSONParser jsonParser;

	// constructor
	public UserFunctions() {
		jsonParser = new JSONParser();
	}

	/**
	 * Function to Register
	 */
	public JSONObject registerUser(String username, String email,
			String workshop) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(Config.KEY_TAG, Config.REGISTER_TAG));
		params.add(new BasicNameValuePair(Config.KEY_USERNAME, username));
		params.add(new BasicNameValuePair(Config.KEY_EMAIL, email));
		params.add(new BasicNameValuePair(Config.KEY_WORKSHOP, workshop));
		JSONObject json = jsonParser.getJSONFromUrl(Config.URL, params);
		return json;
	}
	
	/**
	 * Function to get all registered users
	 */
	public JSONObject getUsers() {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(Config.KEY_TAG, Config.GET_USERS_TAG));
		JSONObject json = jsonParser.getJSONFromUrl(Config.URL, params);
		return json;
	}
}
