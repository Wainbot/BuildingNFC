package fr.unice.fr.rfid.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import fr.unice.idse.model.Building;
import fr.unice.idse.model.Model;
import fr.unice.idse.services.BuildingService;

public class BuildingServiceTest extends JerseyTest {
	private Model model;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(BuildingService.class).getApplication();
	}
	/*
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
		model.getApp().addBuilding(new Building("iut"));
		model.getApp().addBuilding(new Building("polytech"));
		
		Response response = target("/building").request().get();
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(200, response.getStatus());
	    assertEquals(2, json.getJSONArray("buildings").length());
	    assertEquals("{\"name\":\"iut\"}", json.getJSONArray("buildings").get(0).toString());
	    assertEquals("{\"name\":\"polytech\"}", json.getJSONArray("buildings").get(1).toString());
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourneUneErreur401SiLeJSONEstMalForme() throws JSONException {
		Response response = target("/building").request().put(Entity.json("{"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(401, response.getStatus());
	    assertEquals("Fail to create JSON object", json.getString("error"));
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourneUneErreur401SiIlManqueLeNameDansLeJSON() throws JSONException {
		Response response = target("/building").request().put(Entity.json("{\"tagid\":\"049e0bb27a4880\"}"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(401, response.getStatus());
	    assertEquals("JSON invalid name is missing", json.getString("error"));
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourneUneErreur401SiIlManqueLeTagDansLeJSON() throws JSONException {
		Response response = target("/building").request().put(Entity.json("{\"name\":\"iut\"}"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(401, response.getStatus());
	    assertEquals("JSON invalid tagid is missing", json.getString("error"));
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourneUneErreur405SiLeNomDuBuildingExisteDeja() throws JSONException {
		model.getApp().addBuilding(new Building("iut"));
		
		Response response = target("/building").request().put(Entity.json("{\"name\":\"iut\",\"tagid\":\"049e0bb27a4880\"}"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(405, response.getStatus());
	    assertEquals("Invalid building name", json.getString("error"));
	}
	
	@Test
	public void testAddBuildingEnVerifiantSiIlRetourne200Ok() throws JSONException {
		Response response = target("/building").request().put(Entity.json("{\"name\":\"iut\",\"tagid\":\"049e0bb27a4880\"}"));
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(200, response.getStatus());
	    assertEquals("Building added succesfully", json.getString("success"));
	}
	
	@Test
	public void testGetBuildingEnVerifiantSiIlTrouveBienLeBatiment() throws JSONException {
		model.getApp().addBuilding(new Building("iut", "myToken"));
		
		Response response = target("/building/myToken").request().get();
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(200, response.getStatus());
	    assertEquals("iut", json.getString("name"));
	}
	
	@Test
	public void testGetBuildingEnVerifiantSiIlNeTrouvePasLeBatiment() throws JSONException {
		Response response = target("/building/iut").request().get();
		JSONObject json = new JSONObject(response.readEntity(String.class));
		
	    assertEquals(405, response.getStatus());
	    assertEquals("The building with this tag id was not found", json.getString("error"));
	}
	*/
}
