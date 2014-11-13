package de.tudarmstadt.networkcoverage.models;

import java.util.ArrayList;
import java.util.List;

public class Path {
	protected List<CumulativeLocation> points;

	protected Path() {
		points = new ArrayList<CumulativeLocation>();
	}

	public void addPoint(CumulativeLocation point) {
		points.add(point);
	}

	public static List<Path> extractPaths(List<CumulativeLocation> points) {
		if (points == null || points.size() == 0)
			return null;

		List<Path> result = new ArrayList<Path>();
		Path path = new Path();
		path.addPoint(points.get(0));

		for (int i = 1; i < points.size(); i++) {
			// More than one hour
			if ((points.get(i).getStart().getTime() - points.get(i - 1)
					.getEnd().getTime()) > 60 * 60 * 1000) {
				result.add(path);
				path = new Path();
			}

			path.addPoint(points.get(i));
		}
		result.add(path);

		return result;
	}

	public List<CumulativeLocation> getPoints() {
		return this.points;
	}

	@Override
	public String toString() {
		if (points == null || points.size() == 0)
			return "";
		StringBuilder result = new StringBuilder();

		for (CumulativeLocation point : points)
			result.append(point.toString() + ",");

		return result.toString();
	}
}
