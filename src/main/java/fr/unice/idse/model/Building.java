package fr.unice.idse.model;

import java.util.ArrayList;

public class Building {
	protected String name;
	protected ArrayList<Floor> floors;
	
	public Building(String name) {
		this.floors = new ArrayList<Floor>();
		this.name = name;
	}
	
	public void addFloor(Floor floor) {
		this.floors.add(floor);
	}
	
	public Floor getFloorAt(int index) {
		if(index < this.floors.size() && index > 0) {
			return this.floors.get(index);
		}
		return null;
	}
	
	public String getName() {
		return this.name;
	}
}
