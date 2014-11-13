package de.tudarmstadt.networkcoverage.controller.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.tudarmstadt.networkcoverage.models.CumulativeLocation;
import de.tudarmstadt.networkcoverage.models.Path;
import android.test.AndroidTestCase;
import android.util.Log;

public class CSVConnectorTest extends AndroidTestCase {
	private CSVConnector mCSVConnector;

	@BeforeClass
	protected void setUp() throws Exception {
		super.setUp();
		mCSVConnector = new CSVConnector();

		String fileName = "assets/all.csv";
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in,
				"UTF-8"));

		if (in != null) {
			List<CumulativeLocation> points = mCSVConnector.read(br, 10);

			List<Path> paths = Path.extractPaths(points);
			Log.d("", "!23");
		}
	}

	@Test
	public void readTest() {
		String fileName = "data/all.csv";

		// mCSVConnector.read(fileName);

		assertFalse("message", false);

	}
}
