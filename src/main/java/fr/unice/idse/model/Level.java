package fr.unice.idse.model;

public class Level {
	private int level;
	private byte[] image;

	public Level(int level, byte[] image) {
		this.level = level;
		this.image = image;
	}
	
	public int getLevel() {
		return level;
	}
	
	public byte[] getImage() {
		return image;
	}
}
