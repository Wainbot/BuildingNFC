package fr.unice.idse.model;

import java.util.ArrayList;

public class Building {
	protected String name;
	protected String description;
 	protected ArrayList<Level> levels;
	protected byte[] image;
	
	public Building(String name) {
		this.levels = new ArrayList<Level>();
		this.name = name;
	}
	
	public void addFloor(Level level) {
		this.levels.add(level);
	}
	
	public Level getFloorAt(int index) {
		if(index < this.levels.size() && index > 0) {
			return this.levels.get(index);
		}
		return null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public byte[] getImage() {
		return image;
	}
}
