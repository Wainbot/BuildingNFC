package fr.unice.idse.model;

import java.util.ArrayList;
import java.util.List;

public class Building {
	protected String name, description, tag;
 	protected ArrayList<Level> levels;
	protected Picture picture;
	
	public Building(String name, String tag) {
		this.levels = new ArrayList<Level>();
		this.name = name;
		this.tag = tag;
	}
	
	public Building(String name) {
		this(name, "");
	}
	
	public void addLevel(Level level) {
		this.levels.add(level);
	}
	
	public Level getLevelAt(int index) {
		if(index < this.levels.size() && index > 0) {
			return this.levels.get(index);
		}
		return null;
	}
	
	public List<Level> getFloors() {
		return this.levels;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Picture getPicture() {
		return picture;
	}
	
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
}
