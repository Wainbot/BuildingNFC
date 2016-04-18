package fr.unice.idse.model;

public class Level {
	private int level;
	private Picture picture;

	public Level(int level) {
		this(level, null);
	}
	
	public Level(int level, Picture picture) {
		this.level = level;
		this.picture = picture;
	}
	
	public int getLevel() {
		return level;
	}
	
	public Picture getPicture() {
		return picture;
	}
}
