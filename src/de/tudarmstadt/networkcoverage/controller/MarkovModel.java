package de.tudarmstadt.networkcoverage.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.tudarmstadt.networkcoverage.models.CumulativeLocation;

public class MarkovModel {

	// Point:CumulativeLocation, Probability: Double
	protected HashMap<CumulativeLocation, HashMap<CumulativeLocation, Double>> model;

	public MarkovModel() {
		model = new HashMap<CumulativeLocation, HashMap<CumulativeLocation, Double>>();
	}

	public Double nextStepQuality(CumulativeLocation currentPoint) {
		HashMap<CumulativeLocation, Double> nextPoints = model
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
		return addPoint(point, new HashMap<CumulativeLocation, Double>());
	}

	public boolean addPoint(CumulativeLocation point,
			HashMap<CumulativeLocation, Double> relatedPoint) {
		try {
			if (model.containsKey(point))
				return false;
			model.put(point, relatedPoint);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public void loadPoints(List<CumulativeLocation> point) {

	}

}
