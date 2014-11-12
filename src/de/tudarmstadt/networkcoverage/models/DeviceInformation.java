/**
 * 
 */
package de.tudarmstadt.networkcoverage.models;

/**
 * @author fkaup
 *
 */
public class DeviceInformation {
	
	private String deviceName;
	private String imei;
	private String androidID;
	
	

	public DeviceInformation () {
		deviceName = "unknown";
		imei       = "unknown";
		androidID = "unknown";
	}
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getAndroidID() {
		return androidID;
	}

	public void setAndroidID(String androidID) {
		this.androidID = androidID;
	}

}
