package de.tudarmstadt.networkcoverage.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.tudarmstadt.networkcoverage.models.CumulativeLocation;
import de.tudarmstadt.networkcoverage.models.Path;

public class MarkovModel {

	// Point:CumulativeLocation, Probability: Double
	protected HashMap<CumulativeLocation, HashMap<CumulativeLocation, Integer>> model;

	public MarkovModel() {
		model = new HashMap<CumulativeLocation, HashMap<CumulativeLocation, Integer>>();
	}

	public Double nextStepQuality(CumulativeLocation currentPoint) {
		HashMap<CumulativeLocation, Integer> nextPoints = model
				.get(currentPoint);
		Iterator it = nextPoints.entrySet().iterator();
		int counter = 0;
		double sum = 0;
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			CumulativeLocation point = (CumulativeLocation) pairs.getKey();
			double probability = (Double) pairs.getValue();

			sum += probability * point.getQuality();
			counter++;
		}
		return sum / counter;
	}

	public boolean addPoint(CumulativeLocation point) {
		return addPoint(point, new HashMap<CumulativeLocation, Integer>());
	}

	public boolean addPoint(CumulativeLocation point,
			HashMap<CumulativeLocation, Integer> relatedPoint) {
		try {
			if (model.containsKey(point))
				return false;
			model.put(point, relatedPoint);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean addPoint(CumulativeLocation point,
			CumulativeLocation nextPoint) {
		if (model.containsKey(point)) {
			HashMap<CumulativeLocation, Integer> relatedPoint = model
					.get(point);
			if (relatedPoint.containsKey(nextPoint)) {
				relatedPoint.put(nextPoint, relatedPoint.get(nextPoint) + 1);
			} else
				relatedPoint.put(nextPoint, 1);

		} else {
			HashMap<CumulativeLocation, Integer> relatedPoint = new HashMap<CumulativeLocation, Integer>();
			relatedPoint.put(nextPoint, 1);
			model.put(point, relatedPoint);
		}

		return true;
	}

	public void loadPaths(List<Path> paths) {
		for (Path path : paths) {
			List<CumulativeLocation> points = path.getPoints();
			if (points.size() > 1)
				for (int i = 1; i < points.size(); i++)
					addPoint(points.get(i - 1), points.get(i));
		}
	}
}
