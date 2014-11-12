package de.tudarmstadt.networkcoverage.models;

import java.util.ArrayList;

import android.location.Location;

public abstract class CoveragePoint {
	protected Location location;
	protected DeviceInformation deviceInformation;
	protected PingData pingData;
	protected BenchmarkDataPoint benchmarkData;
	protected boolean isSent;
	protected long databaseId;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location newLocation) {
		this.location = newLocation;
	}

	public DeviceInformation getDeviceInformation() {
		return deviceInformation;
	}

	public void setDeviceInformation(DeviceInformation deviceInformation) {
		this.deviceInformation = deviceInformation;
	}

	/**
	 * Checks the data point for validity
	 * 
	 * @return
	 */
	public boolean isValid() {
		boolean status = false;
		if ((location != null) && location.getLatitude() != 0.0
				&& location.getLongitude() != 0.0) {
			status = true;
		}
		return status;
	}

	public boolean isSent() {
		return isSent;
	}

	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}

	public boolean hasPingData() {
		return pingData != null;
	}

	public boolean hasBenchmarkData() {
		return benchmarkData != null;
	}

	public String getPingDataString() {
		return pingData.getPingResults();
	}

	public PingData getPingData() {
		return pingData;
	}

	public BenchmarkDataPoint getBenchmarkData() {
		return benchmarkData;
	}

	public Long getDownloadSpeed() {
		return benchmarkData.getDownloadSpeed();
	}

	public Long getTrafficDown() {
		return benchmarkData.getTrafficDown();
	}

	public void setBenchmarkResults(BenchmarkDataPoint benchmark) {
		benchmarkData = benchmark;
	}

	@Deprecated
	public void setPingResults(ArrayList<String> ping) {
		pingData = new PingData();
		pingData.setPingResults(ping);
	}

	public void setPing(PingData pingData) {
		this.pingData = pingData;
	}

	public long getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(long databaseId) {
		this.databaseId = databaseId;
	}
}
