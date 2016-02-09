package fr.unice.fr.rfid.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unice.idse.model.Building;
import fr.unice.idse.model.Floor;

public class BuildingTest extends Building {

	public BuildingTest() {
		super("iut");
	}

	@After
	public void init() {
		name = null;
		floors = null;
	}
	
	@Test
	public void testConstructeurBuildingInitVariable() {
		assertNotNull(floors);
		assertEquals("iut", name);
	}
	
	@Test
	public void testAddFloorIsAddinfFloorToTheFloorsList() { // LOL
		addFloor(new Floor(0));
		assertEquals(1, floors.size());
	}
	
	@Test
	public void testGetFloorAtReturnNullIfTheIndexIsLessThanZero() {
		assertNull(getFloorAt(-42));
	}
	
	@Test
	public void testGetFloorAtReturnNullIfTheIndexIsGreaterThanTheFloorsListSize() {
		floors.add(new Floor(0));
		floors.add(new Floor(1));
		
		assertNull(getFloorAt(42));
	}
	
	@Test
	public void testGetFloorAtReturnNotNullIfTheIndexIsLessThanTheSizeAndPositive() {
		floors.add(new Floor(0));
		floors.add(new Floor(1));
		
		assertNotNull(getFloorAt(1));
	}
	
	@Test
	public void testGetFloorAtReturnTheRightFloorAtTheWantedIndex() {
		floors.add(new Floor(0));
		Floor floor = new Floor(1);
		floors.add(floor);
		
		assertEquals(floor, getFloorAt(1));
	}
	
	@Test
	public void testGetNameReturnTheRightName() {
		assertEquals("iut", getName());
	}

}
