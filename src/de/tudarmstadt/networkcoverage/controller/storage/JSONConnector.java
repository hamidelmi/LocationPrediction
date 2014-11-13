package de.tudarmstadt.networkcoverage.controller.storage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;
import de.tudarmstadt.networkcoverage.models.CoverageArea;
import de.tudarmstadt.networkcoverage.models.CumulativeLocation;
import de.tudarmstadt.networkcoverage.models.Path;

public class JSONConnector implements IDBConnector {
	public ArrayList<CoverageArea> readRecentNeighboring(int numberLastElements) {
		return null;
	}

	@SuppressLint("SimpleDateFormat")
	public List<Path> read(InputStream in, int count) {
		List<Path> result = new ArrayList<Path>();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyyMMdd'T'hhmmss");
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			JSONObject jsonObject = new JSONObject(sb.toString());
			JSONArray array = jsonObject.getJSONArray("features");
			for (int i = 0; i < array.length() && i < count; i++) {
				JSONObject obj = array.getJSONObject(i);
				JSONObject properties = obj.getJSONObject("properties");
				Date startTime = formatter.parse(properties
						.getString("startTime"));
				Date endTime = formatter.parse(properties.getString("endTime"));

				JSONArray coordinates = obj.getJSONObject("geometry")
						.getJSONArray("coordinates");

				for (int j = 0; j < coordinates.length(); j++) {
					JSONArray rawPoints = coordinates.getJSONArray(j);

					List<CumulativeLocation> points = new ArrayList<CumulativeLocation>();
					for (int k = 0; k < rawPoints.length(); k++)

						points.add(new CumulativeLocation(startTime, endTime,
								rawPoints.getJSONArray(k).getDouble(1),
								rawPoints.getJSONArray(k).getDouble(0), 1));
					result.addAll(Path.extractPaths(points));

				}
			}
		} catch (Exception ex) {
			Log.e("JSONConnector", ex.getMessage());
		}
		return result;
	}
}
