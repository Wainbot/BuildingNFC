package fr.unice.idse.util;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.unice.idse.model.Building;
import fr.unice.idse.model.Level;
import fr.unice.idse.model.Picture;

public class BusinessQuery {
	private static Connexion connexion = new Connexion(Config.ip + ":" + Config.port + "/" + Config.dbName
			, Config.user, Config.pass);
	
	public static ArrayList<Building> listBuilding() {
		ArrayList<Building> listBuilding = new ArrayList<Building>();
		try {
			connexion.connect();
			
			ResultSet result = connexion.query(
					"SELECT building.id AS buildingId, name, tag, description, id_picture, picture "
					+ "FROM building, picture "
					+ "WHERE building.id_picture = picture.id;");

			while(result.next()) {
				Building building = new Building(result.getString("name"), result.getString("tag"));
				building.setDescription(result.getString("description"));
				Blob blob = result.getBlob("picture");
				building.setPicture(new Picture(result.getInt("id_picture"), blob.getBytes(1, (int)blob.length())));
				listBuilding.add(building);
			}
			result.close();
			
			ResultSet result2 = connexion.query(
					"SELECT tag, level_number, level.id_picture AS picutreId, picture "
					+ "FROM building, level, picture "
					+ "WHERE building.id = level.id_building "
					+ "AND picture.id = level.id_picture;");
			
			while(result2.next()) {
				Blob blob = result2.getBlob("picture");
				Picture pic = new Picture(result2.getInt("picutreId"), blob.getBytes(1, (int)blob.length()));
				Level level = new Level(result2.getInt("level_number"), pic);
				for(Building build : listBuilding) {
					if(build.getTag().equals(result2.getString("tag"))) {
						build.addLevel(level);
					}
				}
			}
			result2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connexion.close();
		return listBuilding;			
	}
}
