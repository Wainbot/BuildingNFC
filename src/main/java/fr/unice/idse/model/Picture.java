package fr.unice.idse.model;

public class Picture {
	private int id;
	private byte[] picture;

	public Picture() {
	}

	public Picture(int id, byte[] picture) {
		this.id = id;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

}
