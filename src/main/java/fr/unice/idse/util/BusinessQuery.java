package fr.unice.idse.util;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.unice.idse.model.Building;

public class BusinessQuery {
	private static Connexion connexion = new Connexion(Config.ip + ":" + Config.port + "/" + Config.dbName
			, Config.user, Config.pass);
	
	public static ArrayList<Building> listBuilding() {
		ArrayList<Building> listBuilding = new ArrayList<Building>();
		try {
			connexion.connect();
			
			ResultSet result = connexion.query("SELECT * FROM building");

			Building building = null;
			while(result.next()) {
				building = new Building(result.getString("name"), result.getString("tag"));
				building.setDescription(result.getString("description"));
				Blob blob = result.getBlob("image");
				building.setImage(blob.getBytes(1, (int)blob.length()));
				
				listBuilding.add(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connexion.close();
		return listBuilding;			
	}
}
