package de.tudarmstadt.networkcoverage.controller.storage;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.annotation.SuppressLint;
import android.util.Log;
import com.opencsv.CSVReader;
import de.tudarmstadt.networkcoverage.models.CoverageArea;
import de.tudarmstadt.networkcoverage.models.CumulativeLocation;

public class CSVConnector implements IDBConnector {

	@Override
	public ArrayList<CoverageArea> readRecentNeighboring(int numberLastElements) {
		return null;
	}

	@SuppressLint("SimpleDateFormat")
	public List<CumulativeLocation> read(Reader reader, int count) {
		if (reader == null)
			return null;
		List<CumulativeLocation> result = new ArrayList<CumulativeLocation>();
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(reader);
			String[] nextLine;
			int i = 0;

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd'T'hh:mm:ss");

			while ((nextLine = csvReader.readNext()) != null) {
				try {
					result.add(new CumulativeLocation(formatter
							.parse(nextLine[2]), formatter.parse(nextLine[3]),
							Double.parseDouble(nextLine[5]), Double
									.parseDouble(nextLine[6]), 0));
				} catch (Exception ex) {
					Log.e("CSVConnector", ex.getMessage());
				}

				if (i++ > count)
					break;
			}
			csvReader.close();
		} catch (Exception ex) {
			Log.e("CSVConnector", ex.getMessage());
		}

		return result;
	}
}
