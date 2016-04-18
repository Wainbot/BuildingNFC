package fr.unice.fr.rfid.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unice.idse.model.Building;
import fr.unice.idse.model.Level;

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
		addLevel(new Level(0, new byte[0]));
		assertEquals(1, levels.size());
	}
	
	@Test
	public void testGetFloorAtReturnNullIfTheIndexIsLessThanZero() {
		assertNull(getLevelAt(-42));
	}
	
	@Test
	public void testGetFloorAtReturnNullIfTheIndexIsGreaterThanTheFloorsListSize() {
		levels.add(new Level(0, new byte[0]));
		levels.add(new Level(1, new byte[0]));
		
		assertNull(getLevelAt(42));
	}
	
	@Test
	public void testGetFloorAtReturnNotNullIfTheIndexIsLessThanTheSizeAndPositive() {
		levels.add(new Level(0, new byte[0]));
		levels.add(new Level(1, new byte[0]));
		
		assertNotNull(getLevelAt(1));
	}
	
	@Test
	public void testGetFloorAtReturnTheRightFloorAtTheWantedIndex() {
		levels.add(new Level(0, new byte[0]));
		Level level = new Level(1, new byte[0]);
		levels.add(level);
		
		assertEquals(level, getLevelAt(1));
	}
	
	@Test
	public void testGetNameReturnTheRightName() {
		assertEquals("iut", getName());
	}

}
