package com.example.locationprediction.model;

import java.sql.Timestamp;

public class Coordinate {
	private Timestamp timestamp;
	private double x, y;

	public Coordinate() {

	}

	public Coordinate(double x, double y) {
		timestamp = new Timestamp(new java.util.Date().getTime());
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Time:" + timestamp + " (" + this.x + "," + this.y + ")";
	}
}
