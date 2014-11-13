package de.tudarmstadt.networkcoverage.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

public class CumulativeLocation {
	protected Date start, end;
	protected double x;
	protected double y;
	protected double quality;

	public CumulativeLocation(Date start, Date end, double x, double y,
			double quality) {
		this.start = start;
		this.end = end;
		this.x = fix(x);
		this.y = fix(y);
		this.quality = quality;
	}

	protected double fix(double n) {
		return ((int) (n * 1000)) / 1000.0;
	}

	public double getQuality() {
		return this.quality;
	}

	public Date getStart() {
		return this.start;
	}

	public Date getEnd() {
		return this.end;
	}

	@Override
	public String toString() {
		return toString(true);
	}

	@SuppressLint({ "DefaultLocale", "SimpleDateFormat" })
	public String toString(boolean dates) {
		if (dates) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd'T'hh:mm:ss");

			return String.format("[%s-%s](%6.3f,%6.3f)",
					formatter.format(this.start), formatter.format(this.end),
					this.x, this.y);
		} else
			return String.format("(%6.3f,%6.3f)", this.x, this.y);
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
