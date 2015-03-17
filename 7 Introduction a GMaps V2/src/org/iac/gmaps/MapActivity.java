package org.iac.gmaps;

import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends ActionBarActivity implements LocationListener {

	private static final long TIME_UPDATE = 20 * 1000; // 20 s
	private static final float DISTANCE_UPDATE = 10; // 10 m
	private GoogleMap mMap;
	private SupportMapFragment mapFragment;
	private static final LatLng TUNIS = new LatLng(36.818810000000000000,
			10.165960000000041000);
	private static final LatLng INSAT = new LatLng(36.8429448,10.1963753);
	private static final LatLng AEROPORT = new LatLng(36.8463673,10.2274661);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getBaseContext());

		if (status != ConnectionResult.SUCCESS) { // Google Play Services are
													// not available

			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
					requestCode);
			dialog.show();

		} else {
			// Getting reference to the SupportMapFragment
			mapFragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);
			// Getting reference to the Google Map
			mMap = mapFragment.getMap();
			// Enable MyLocation Button in the Map
			mMap.setMyLocationEnabled(true);
			
			// Set the Map Type to Terrain
			//mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			
			// Getting LocationManager object from System Service
			// LOCATION_SERVICE
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			// Creating a criteria object to retrieve provider
			Criteria criteria = new Criteria();
			// Getting the name of the best provider
			String provider = locationManager.getBestProvider(criteria, true);
			// Getting Current Location From GPS
			Location location = locationManager.getLastKnownLocation(provider);

			if (location != null) {
				onLocationChanged(location);
			}

			locationManager.requestLocationUpdates(provider, TIME_UPDATE,
					DISTANCE_UPDATE, this);
			
			
			// Adding A default Red Marker to the Map
			mMap.addMarker(new MarkerOptions().position(TUNIS));
			
			// Adding A default Blue Marker to the Map + Info Window
						mMap.addMarker(new MarkerOptions().position(AEROPORT).title("L'aéroport")
								.snippet("L'aéroport de Tunis Carthage")
								.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
								);
			
			// Adding A Custom Marker to the Map + Info Window
			mMap.addMarker(new MarkerOptions().position(INSAT).title("INSAT")
					.snippet("Institut National des Scienes Appliqués et de Technologie")
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
					);
			
			
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
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
	public void onLocationChanged(Location location) {
		LatLng currentPosition = new LatLng(location.getLatitude(),
				location.getLongitude());
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition,
				10));
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// Appelée lorsque le provider devient récemment disponible aprés une
		// période d'indisponibilité.
	}

	@Override
	public void onProviderEnabled(String provider) {
		// Appelé lorsque le provider est activé par l'utilisateur.
	}

	@Override
	public void onProviderDisabled(String provider) {
		// Appelé lorsque le provider est désactivé par l'utilisateur.
	}

	
}
