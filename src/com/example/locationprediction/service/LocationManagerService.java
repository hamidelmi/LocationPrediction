package com.example.locationprediction.service;

import android.app.IntentService;
import android.content.Intent;

public class LocationManagerService extends IntentService {

	public LocationManagerService() {
		super("LocationManagerService");
	}

	@Override
	protected void onHandleIntent(Intent workIntent) {
		if (workIntent != null) {
			String dataString = workIntent.getDataString();
		}
	}
}
