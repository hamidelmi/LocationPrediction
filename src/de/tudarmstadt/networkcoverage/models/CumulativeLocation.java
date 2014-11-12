package de.tudarmstadt.networkcoverage.models;

import android.annotation.SuppressLint;

public class CumulativeLocation {
	protected double x;
	protected double y;
	protected double quality;

	public CumulativeLocation(double x, double y, double quality) {
		this.x = fix(x);
		this.y = fix(y);
		this.quality = quality;
	}

	protected double fix(double n) {
		return ((int) (n * 1000)) / 1000;
	}

	public double getQuality() {
		return this.quality;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public String toString() {
		return String.format("%6.3f-%6.3f", this.x, this.y);
	}

	@Override
	public boolean equals(Object l) {
		if (l instanceof CumulativeLocation) {
			CumulativeLocation t = (CumulativeLocation) l;
			return this.x == t.x && this.y == t.y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (int) (x * 1000) * 100000 + (int) (y * 1000);
	}
}
