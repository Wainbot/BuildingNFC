package fr.unice.idse.services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import fr.unice.idse.model.Building;
import fr.unice.idse.model.Model;

@Path("/building")
public class BuildingService {
	/**
	 * Get the list of building in the app.
	 * @return Response 200 With a json containing the list of buildings : <br />
	 *         {"buildings":[{"name":String}]}
	 * @throws JSONException - If there is a syntax error in the source string. 
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMaps() throws JSONException {
		Model model = Model.getInstance();
		ArrayList<Building> buildings = model.getApp().getBuildings();
		
		JSONArray ja = new JSONArray();
		for(Building building : buildings) {
			JSONObject jo = new JSONObject();
			jo.put("name", building.getName());
			ja.put(jo);
		}
		JSONObject json = new JSONObject();
		json.put("buildings", ja);
		
		return Response.status(200).entity(json.toString()).build();
	}
	
	/**
	 * Add a building in the app.
	 * @param strJson A JSON String containing the name and is tagid arguments : <br/>
	 *        {"name": String, "tagid": String}
	 * @return Response 401 For invalid JSON <br/>
	 *         Response 405 If the building name or tagid is already taken <br/>
	 *         Response 200 If the building was succesfully added
	 */
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBuilding(String strJson) {
		try {
			JSONObject json = new JSONObject(strJson);
			
			if(!json.has("name")) {
				return Response.status(401).entity("{\"error\":\"JSON invalid name is missing\"}").build();
			}
			
			if(!Model.getInstance().getApp().addBuilding(new Building(json.getString("name")))) {
				return Response.status(405).entity("{\"error\":\"Invalid building\"}").build();
			}
			
		} catch (JSONException e) {
			return Response.status(401).entity("{\"error\":\"Fail to create JSON object\"}").build();
		}
		return Response.status(200).entity("{\"success\":\"Building added succesfully\"}").build();
	}
}
