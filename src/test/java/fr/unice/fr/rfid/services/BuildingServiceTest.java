package fr.unice.fr.rfid.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import fr.unice.idse.model.Building;
import fr.unice.idse.model.Level;
import fr.unice.idse.model.Model;
import fr.unice.idse.model.Picture;
import fr.unice.idse.services.BuildingService;

public class BuildingServiceTest extends JerseyTest {
	private Model model;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(BuildingService.class).getApplication();
	}
	
	@Before
	public void init() {
		Model.clear();
		model = Model.getInstance();
	}
	
	@Test
	public void testgetBuildingsEnVerifiantSiIlRetourneUneListeVideSiAucunBuildingsExiste() throws JSONException {
		model.getApp().setBuildings(new ArrayList<Building>());
		Response response = target("/building").request().get();
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(200, response.getStatus());
	    assertEquals(0, json.getJSONArray("buildings").length());
	}
	
	@Test
	public void testgetBuildingsEnVerifiantSiIlRetourneLaListeDesBuildingsExiste() throws JSONException {
		model.getApp().setBuildings(new ArrayList<Building>());
		model.getApp().addBuilding(new Building("iut", "tag1"));
		model.getApp().addBuilding(new Building("polytech", "tag2"));
		
		Response response = target("/building").request().get();
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(200, response.getStatus());
	    assertEquals(2, json.getJSONArray("buildings").length());
	    assertEquals("{\"name\":\"iut\",\"tag\":\"tag1\"}", json.getJSONArray("buildings").get(0).toString());
	    assertEquals("{\"name\":\"polytech\",\"tag\":\"tag2\"}", json.getJSONArray("buildings").get(1).toString());
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourneUneErreur401SiLeJSONEstMalForme() throws JSONException {
		Response response = target("/building").request().post(Entity.json("{"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(401, response.getStatus());
	    assertEquals("Fail to create JSON object", json.getString("error"));
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourneUneErreur401SiIlManqueLeNameDansLeJSON() throws JSONException {
		Response response = target("/building").request().post(Entity.json("{\"tagid\":\"049e0bb27a4880\", \"pic\":\"05656\"}"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(401, response.getStatus());
	    assertEquals("JSON invalid name is missing", json.getString("error"));
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourneUneErreur401SiIlManqueLeTagDansLeJSON() throws JSONException {
		Response response = target("/building").request().post(Entity.json("{\"name\":\"iut\", \"pic\":\"05656\"}"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(401, response.getStatus());
	    assertEquals("JSON invalid tagid is missing", json.getString("error"));
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourneUneErreur405SiLeNomDuBuildingExisteDeja() throws JSONException {
		model.getApp().addBuilding(new Building("iut"));
		
		Response response = target("/building").request().post(Entity.json("{\"name\":\"iut\",\"tagid\":\"049e0bb27a4880\", \"pic\":\"05656\"}"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(405, response.getStatus());
	    assertEquals("Invalid building name", json.getString("error"));
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourne200Ok() throws JSONException {
		Response response = target("/building").request().post(Entity.json("{\"name\":\"iut\",\"tagid\":\"049e0bb27a4880\", \"pic\":\"05656\"}"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(200, response.getStatus());
	    assertEquals("Building added succesfully", json.getString("success"));
	}
	
	@Test
	public void testGetBuildingEnVerifiantSiIlTrouveBienLeBatiment() throws JSONException {
		Building building = new Building("iut", "myToken");
		building.addLevel(new Level(0));
		building.setPicture(new Picture(10, null));
		model.getApp().addBuilding(building);
		
		Response response = target("/building/myToken").request().get();
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(200, response.getStatus());
	    assertEquals("iut", json.getString("name"));
	    assertEquals(0, json.getJSONArray("levels").get(0));
	}
	
	@Test
	public void testGetBuildingEnVerifiantSiIlNeTrouvePasLeBatiment() throws JSONException {
		Response response = target("/building/iut").request().get();
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(405, response.getStatus());
	    assertEquals("The building with this tag id was not found", json.getString("error"));
	}
	
}
