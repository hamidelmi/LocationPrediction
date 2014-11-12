package de.tudarmstadt.networkcoverage.controller.storage;

import java.util.ArrayList;

import de.tudarmstadt.networkcoverage.models.CoverageArea;

public interface IDBConnector {
	public ArrayList<CoverageArea> readRecentNeighboring(int numberLastElements);
}
