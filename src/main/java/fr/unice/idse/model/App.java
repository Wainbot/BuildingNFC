package fr.unice.idse.model;

import java.util.ArrayList;

public class App {
	protected ArrayList<Building> buildings;
	
	public App() {
		buildings = new ArrayList<Building>();
	}
	
	public boolean addBuilding(Building building) {
		boolean res = false;
		if(res = (findBuildingByName(building.getName()) == null)) {
			buildings.add(building);
		}
		return res;
	}
	
	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public Building findBuildingByTagId(String tagId) {
		Building building = null;
		int i = 0;
		while(i < buildings.size() && building == null) {
			if(buildings.get(i).getName().equals(tagId)) {
				building = buildings.get(i);
			}
			i++;
		}
		return building;
	}
	
	public Building findBuildingByName(String name) {
		Building building = null;
		int i = 0;
		while(i < buildings.size() && building == null) {
			if(buildings.get(i).getName().equals(name)) {
				building = buildings.get(i);
			}
			i++;
		}
		return building;
	}
	
}
