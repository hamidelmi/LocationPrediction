package com.example.locationprediction;

import com.example.locationprediction.service.LocationManagerService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
	private Button startTrackingButton, stopTrackingButton;
	private TextView statusTextView;

	private Intent locationService;
	private LocationManager locationManager;
	private LocationListener locationListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		statusTextView = (TextView) findViewById(R.id.statusTextView);
		startTrackingButton = (Button) findViewById(R.id.startTrackingButton);
		stopTrackingButton = (Button) findViewById(R.id.stopTrackingButton);

		stopTrackingButton.setEnabled(false);

		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		startTrackingButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				statusTextView.setText("Tracking ...");
				startTrackingButton.setEnabled(false);
				stopTrackingButton.setEnabled(true);

				locationService = new Intent(getApplicationContext(),
						LocationManagerService.class);

				startService(locationService);

				// try {
				// locationManager.requestLocationUpdates(
				// LocationManager.NETWORK_PROVIDER, 1000, 0,
				// locationListener);
				// } catch (Exception ex) {
				// Log.d("t", ex.getMessage());
				// }
			}
		});

		stopTrackingButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				statusTextView.setText("...");
				startTrackingButton.setEnabled(true);
				stopTrackingButton.setEnabled(false);

				startService(locationService);

				// locationManager.removeUpdates(locationListener);

			}
		});

		locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				// makeUseOfNewLocation(location);

				statusTextView.setText(String.format("(%1$,.3f,%1$,.3f)",
						location.getLongitude(), location.getLatitude()));

			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
