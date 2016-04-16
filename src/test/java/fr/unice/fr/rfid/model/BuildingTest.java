package fr.unice.fr.rfid.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Test;

import fr.unice.idse.model.Building;
import fr.unice.idse.model.Level;
import fr.unice.idse.model.Picture;

public class BuildingTest extends Building {

	public BuildingTest() {
		super("iut", "0b5dsqdqzd0816");
	}

	@After
	public void init() {
		name = null;
		levels = null;
	}
	
	@Test
	public void testConstructeurBuildingInitVariable() {
		assertNotNull(levels);
		assertEquals("iut", name);
	}
	
	@Test
	public void testAddFloorIsAddinfFloorToTheFloorsList() { // LOL
		addFloor(new Level(0, new Picture()));
		assertEquals(1, levels.size());
	}
	
	@Test
	public void testGetFloorAtReturnNullIfTheIndexIsLessThanZero() {
		assertNull(getFloorAt(-42));
	}
	
	@Test
	public void testGetFloorAtReturnNullIfTheIndexIsGreaterThanTheFloorsListSize() {
		levels.add(new Level(0, new Picture()));
		levels.add(new Level(1, new Picture()));
		
		assertNull(getFloorAt(42));
	}
	
	@Test
	public void testGetFloorAtReturnNotNullIfTheIndexIsLessThanTheSizeAndPositive() {
		levels.add(new Level(0, new Picture()));
		levels.add(new Level(1, new Picture()));
		
		assertNotNull(getFloorAt(1));
	}
	
	@Test
	public void testGetFloorAtReturnTheRightFloorAtTheWantedIndex() {
		levels.add(new Level(0, new Picture()));
		Level level = new Level(1, new Picture());
		levels.add(level);
		
		assertEquals(level, getFloorAt(1));
	}
	
	@Test
	public void testGetNameReturnTheRightName() {
		assertEquals("iut", getName());
	}

}
