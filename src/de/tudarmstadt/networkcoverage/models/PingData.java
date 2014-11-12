package de.tudarmstadt.networkcoverage.models;

import java.util.ArrayList;

/**
 * Stores the ping information which was measured before
 * @author jomrich
 *
 */

public class PingData {
	String resultString;
	
	float pingMin;
	float pingMax;
	float pingAvg;
	float pingMeanDev;
	int pingCount;
	int lossRate;
	int packetsTx;
	int packetsRx;	
	
	public PingData() {
		this.pingMin = -1;
		this.pingMax = -1;
		this.pingAvg = -1;
		this.pingMeanDev = -1;
		this.pingCount = 0;
		this.resultString = new String();
	}
	
	public PingData(String pingData) {
		this();
		initFromString(pingData);
	}
	
	public PingData(ArrayList<String> ping) {
		this();
		this.setPingResults(ping);
	}

	public String getPingResults() {
		return resultString;
	}

	/**
	 * Initializes the data fields based on the incoming pingResults
	 * @param pingResults ArrayList<String> containing the ping CLI output
	 */
	@Deprecated
	public void setPingResults(ArrayList<String> pingResults) {
		if (null != pingResults && pingResults.size() > 2) {
			// System.out.println(resultString);
			
			// Ping packets
//			String[] packetString = pingResults.get(pingResults.size() - 3).split(",");
//			String stringPacketsTx = packetString[0].split(" ")[0];
//			String stringPacketsRx = (packetString[1].split(" "))[0];
//			String stringLossRate = packetString[2].split("% ")[0];
//			packetsTx = Integer.parseInt((packetString[0].split(" "))[0]);
//			packetsRx = Integer.parseInt((packetString[1].split(" "))[0]);
//			lossRate  = Integer.parseInt((packetString[2].split("% "))[0]);
			
			// Ping results
			this.resultString = pingResults.get(pingResults.size() - 2);
			String[] metrics = this.resultString.split(",");
			String[] split = metrics[0].split("=");
			String[] data = split[1].split("/");		
			this.pingMin = Float.valueOf(data[0].replace(" ", ""));
			this.pingAvg = Float.valueOf(data[1]);
			this.pingMax = Float.valueOf(data[2]);
			this.pingMeanDev = Float.valueOf(data[3].replace(" ms", ""));

			String packages = pingResults.get(pingResults.size() - 1);
			this.resultString = this.resultString + ", " + packages;
			this.pingCount = Integer.valueOf(packages.replace("packages: ", ""));
			//			System.out.println(pingResults);
		}
	}
	
	public float getPingMin() {
		return pingMin;
	}

	public float getPingMax() {
		return pingMax;
	}

	public float getPingAvg() {
		return pingAvg;
	}

	public float getPingMeanDev() {
		return pingMeanDev;
	}

	public int getPingCount() {
		return pingCount;
	}
	
	public boolean isValid() {
		return (resultString != null && pingMax > 0 && pingMin > 0 && pingAvg > 0 && pingCount > 0 && pingMeanDev > 0);

	}
	
	/* ----- PRIVATE FUNCTIONS ----- */
	
	private boolean initFromString(String pingString) {
		boolean status = false;
		if (null != pingString && !pingString.equals("")) {
			try {
				
				// Ping results
				String[] metrics = pingString.split(",");
				String[] split = metrics[0].split("=");
				String[] data = split[1].split("/");		
				this.pingMin = Float.valueOf(data[0].replace(" ", ""));
				this.pingAvg = Float.valueOf(data[1]);
				this.pingMax = Float.valueOf(data[2]);
				this.pingMeanDev = Float.valueOf(data[3].replace(" ms", ""));
				this.pingCount = Integer.valueOf(metrics[metrics.length - 1].replace("packages: ", "").trim());
				this.resultString = pingString;
				//			System.out.println(pingResults);
				status = true;
			} catch (Exception e) {
				//TODO:
			}
		}
		return status;
	}
}
