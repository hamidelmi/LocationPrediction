package de.tudarmstadt.networkcoverage.models;

//import com.google.gson.Gson;

public class BenchmarkDataPoint {
	long downloadSpeed;
	long uploadSpeed;
	long trafficUp;
	long trafficDown;
	boolean isValid;

	public BenchmarkDataPoint(long dowloadSpeed, long uploadSpeed,
			long trafficDown, long trafficUp) {
		this.uploadSpeed = uploadSpeed;
		this.downloadSpeed = dowloadSpeed;
		this.trafficDown = trafficDown;
		this.trafficUp = trafficUp;
		isValid = true;
	}

	public long getDownloadSpeed() {
		return downloadSpeed;
	}

	public long getUploadSpeed() {
		return uploadSpeed;
	}
	
	public long getTrafficUp() {
		return trafficUp;
	}

	public long getTrafficDown() {
		return trafficDown;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getBenchmarkResults() {
		String outString = getDownloadSpeed() + "\t" + getTrafficDown();
		return outString;
	}
	
//	public String toJsonString(){
//		return new Gson().toJson(this);
//	}
	
	public void setInvalid(){
		isValid = false;
	}
	
	public boolean isValid(){
		return isValid;
	}
}
