package de.tudarmstadt.networkcoverage.controller.storage;

import static org.junit.Assert.*;

import java.io.Console;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.tudarmstadt.networkcoverage.controller.MarkovModel;
import de.tudarmstadt.networkcoverage.models.Path;
import android.app.Application;
import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

public class JSONConnectorTest extends AndroidTestCase {
	private JSONConnector mJSONConnector;

	@Before
	public void setUp() throws Exception {
		mJSONConnector = new JSONConnector();
	}

	// @Test
	// public void testReadRecentNeighboring() {
	// fail("Not yet implemented");
	// }

	@Test
	public void testRead() {

		String fileName = "assets/activities.geojson";

		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream(fileName);

		List<Path> paths = mJSONConnector.read(in, 50);

		MarkovModel mm = new MarkovModel();
		mm.loadPaths(paths);

		String qq = mm.print();
		String qq2 = mm.printForGoogleMap();
		writeToFile(qq2);

		Log.d("t", qq2);
	}

	private Context getTestContext() {
		try {
			Method getTestContext = AndroidTestCase.class
					.getMethod("getTestContext");
			return (Context) getTestContext.invoke(this);
		} catch (final Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	private void writeToFile(String data) {
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					getTestContext().openFileOutput("output.txt",
							Context.MODE_PRIVATE));
			outputStreamWriter.write(data);
			outputStreamWriter.close();
		} catch (Exception e) {
			Log.e("Exception", "File write failed: " + e.toString());
		}
	}

}
