package fr.unice.idse.model;

import java.util.ArrayList;

public class App {
	private ArrayList<Building> buildings;
	
	public App() {
		buildings = new ArrayList<Building>();
	}
	
	public boolean addBuilding(Building building) {
		buildings.add(building);
		return true;
	}
	
	public ArrayList<Building> getBuildings() {
		return buildings;
	}
	
}
