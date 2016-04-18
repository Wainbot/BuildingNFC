package fr.unice.idse.services;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import fr.unice.idse.model.Building;
import fr.unice.idse.model.Level;
import fr.unice.idse.model.Model;
import fr.unice.idse.model.Picture;

/**
 * Building service regroup the different routes calling the methods of the model
 * <h4> Arbre des routes : </h4>
 * <code> 
 * /rest <br/>
 * |-- GET (list buildings) <br/>
 * |-- POST(add building)  <br/>
 * |-- /{tagid} <br/>
 * |-- |-- PUT  (update building info) <br/> // TODO : Implement
 * |-- |-- DELETE (delete building)  <br/> // TODO : Implement
 * |-- |-- GET  (info building + list levels)<br/>
 * |-- |-- POST (add level) <br/> // TODO : Implement
 * |-- |-- /image <br/>
 * |-- |-- |-- /{imageid} <br/> 
 * |-- |-- |-- |-- GET (image) <br/>
 * |-- |-- /{level} <br/>
 * |-- |-- |-- PUT (update level info) <br/> // TODO : Implement
 * |-- |-- |-- DELETE (delete level)  <br/> // TODO : Implement
 * |-- |-- |-- GET (info level) <br/> // TODO : Implement
 * </code>
 */

@Path("/")
public class BuildingService {
	@OPTIONS
	public Response options() {
		return Response.status(200).build();
	}
	
	/**
	 * Get the list of building in the app.
	 * @return Response 200 With a json containing the list of buildings : <br />
	 *         {"buildings":[{"name":String}]}
	 * @throws JSONException - If there is a syntax error in the source string. 
	 * @author Damien Clemenceau
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuildings() throws JSONException {
		Model model = Model.getInstance();
		ArrayList<Building> buildings = model.getApp().getBuildings();
		
		JSONArray ja = new JSONArray();
		for(Building building : buildings) {
			JSONObject jo = new JSONObject();
			jo.put("name", building.getName());
			jo.put("tag", building.getTag());
			ja.put(jo);
		}
		JSONObject json = new JSONObject();
		json.put("buildings", ja);
		
		return Response.status(200).entity(json.toString()).build();
	}
	
	/**
	 * Add a building in the app.
	 * @param strJson A JSON String containing the name and is tagid arguments : <br/>
	 *        {"name": String, "tagid": String, "pic": Blob}
	 * @return Response 401 For invalid JSON <br/>
	 *         Response 405 If the building name or tagid is already taken <br/>
	 *         Response 200 If the building was succesfully added
	 * @author Damien Clemenceau
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBuilding(String strJson) {
		try {
			JSONObject json = new JSONObject(strJson);
			
			if(!json.has("name")) {
				return Response.status(401).entity("{\"error\":\"JSON invalid name is missing\"}").build();
			}
			
			if(!json.has("tagid")) {
				return Response.status(401).entity("{\"error\":\"JSON invalid tagid is missing\"}").build();
			}
			
			if(!json.has("pic")) {
				return Response.status(401).entity("{\"error\":\"JSON invalid pic is missing\"}").build();
			}
			
			Picture pic = new Picture();
			pic.setPicture((byte[])json.get("pic"));
			Building building = new Building(json.getString("name"), json.getString("tagid"));
			building.setPicture(pic);
			if(!Model.getInstance().getApp().addBuilding(building)) {
				return Response.status(405).entity("{\"error\":\"Invalid building name\"}").build();
			}
			
		} catch (JSONException e) {
			return Response.status(401).entity("{\"error\":\"Fail to create JSON object\"}").build();
		}
		return Response.status(200).entity("{\"success\":\"Building added succesfully\"}").build();
	}
	
	/**
	 * Get the building with the specified tag id.
	 * @param tagId The id of a NFC tag
	 * @return Response 405 If the tag id is not attach to a building <br/>
	 *         Response 200 If the building was successfully added
	 * @throws JSONException
	 * @author Damien Clemenceau
	 */
	@GET
	@Path("{tagid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuilding(@PathParam("tagid") String tagId) throws JSONException {		
		Building building = Model.getInstance().getApp().findBuildingByTagId(tagId);
		if(building == null) {
			return Response.status(405).entity("{\"error\":\"The building with this tag id was not found\"}").build();
		}
		
		JSONObject json = new JSONObject();
		json.put("name", building.getName());
		json.put("imageid", building.getPicture().getId());
		
		JSONArray ja = new JSONArray();
		for(Level level : building.getFloors()) {
			JSONObject jo = new JSONObject();
			jo.put("level", level.getLevel());
			jo.put("imageid", level.getPicture().getId());
			ja.put(jo);
		}
		json.put("levels", ja);
		
		return Response.status(200).entity(json.toString()).build();
	}
	
	/**
	 * Get the image of the building with the specified tag id and image id.
	 * @param tagId The id of a NFC tag
	 * @param imageId The id of a image
	 * @return Response 405 If the tag id or image id is not attach to a building <br/>
	 *         Response 200 If the building was successfully added
	 * @throws JSONException
	 * @author Damien Clemenceau
	 */
	@GET
	@Path("{tagid}/image/{imageid}")
	@Produces("image/jpg")
	public Response getBuildingImage(@PathParam("tagid") String tagId, @PathParam("imageid") String imageId) throws JSONException {		
		Building building = Model.getInstance().getApp().findBuildingByTagId(tagId);
		if(building == null) {
			return Response.status(405).entity("{\"error\":\"The building with this tag id was not found\"}").build();
		}
		Picture pic = building.getPicture();
		if(pic.getId() != Integer.parseInt(imageId)) {
			return Response.status(405).entity("{\"error\":\"The picture with this image id was not found\"}").build();
		}
		
		return Response.status(200).entity(new ByteArrayInputStream(pic.getPicture())).build();
	}
}
