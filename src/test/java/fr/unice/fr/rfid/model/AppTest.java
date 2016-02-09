package fr.unice.fr.rfid.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.unice.idse.model.App;
import fr.unice.idse.model.Building;

public class AppTest extends App {
	@Before
	public void init() {
		buildings.clear();
	}
	
	@Test
	public void testAddBuildingsToTheList() {
		Building building = new Building("iut");
		addBuilding(building);
		addBuilding(new Building("Polytech"));
		
		assertEquals(2, buildings.size());
		assertEquals(building, buildings.get(0));
	}
	
	@Test
	public void testAddABuildingsWithTheSameNameInTheListReturnFalse() {
		Building building = new Building("iut");
		addBuilding(building);
		assertFalse(addBuilding(new Building("iut")));
	}
	
	@Test
	public void testAddANewBuildingsWithTheNewNameInTheListReturnTrue() {
		Building building = new Building("iut");
		addBuilding(building);
		assertTrue(addBuilding(new Building("polytech")));
	}
	
	@Test
	public void testGetBuildingsReturnTheRightList() {
		Building building = new Building("iut");
		addBuilding(building);
		addBuilding(new Building("polytech"));
		
		assertEquals(2, getBuildings().size());
		assertEquals(building, getBuildings().get(0));
	}
	
	@Test 
	public void testFindByNameReturnTheWantedBuilding() {
		Building building = new Building("iut");
		buildings.add(building);
		assertEquals(building, findBuildingByTagId("iut"));
	}
	
	@Test 
	public void testFindByNameReturnNullWhenThereAreNotAnyBuildingInTheList() {
		assertNull(findBuildingByTagId("iut"));
	}
	
	@Test 
	public void testFindByNameReturnNullWhenNoBuildingWithTheTagExistTillTheEndOfTheList() {
		addBuilding(new Building("polytech"));
		assertNull(findBuildingByTagId("iut"));
	}
	
	@Test 
	public void testFindByNameReturnNotNullWhenTheBuildingIsFindAtTheEndOfTheList() {
		addBuilding(new Building("iut"));
		addBuilding(new Building("polytech"));
		assertNotNull(findBuildingByTagId("polytech"));
	}
	
	@Test 
	public void testFindByNameReturnNotNullWhenTheBuildingIsFindBeforeTheEndOfTheList() {
		addBuilding(new Building("iut"));
		addBuilding(new Building("polytech"));
		assertNotNull(findBuildingByTagId("iut"));
	}
}
