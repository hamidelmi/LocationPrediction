package de.tudarmstadt.networkcoverage.controller.storage;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import de.tudarmstadt.networkcoverage.models.CoverageArea;

public class CSVConnector implements IDBConnector {

	@Override
	public ArrayList<CoverageArea> readRecentNeighboring(int numberLastElements) {
		return null;
	}

	public void read(String fileName) {
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(fileName));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				System.out.println(nextLine[0] + nextLine[1] + "etc...");
			}
			reader.close();
		} catch (Exception ex) {

		}
	}
}
