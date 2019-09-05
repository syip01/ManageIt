package com.ems.testing;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.ems.entities.Room;
import com.ems.services.RoomServices;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomServicesTest {
	RoomServices roomServices = new RoomServices();
	Room expected;
	Room expected2 = new Room(777, "S101", "Testing Room");
	Room expected3 = new Room(789, "PP420", "Classroom");
	
	@Parameterized.Parameters
	public static Collection params()
	{
		return Arrays.asList(new Object [][]{
			{new Room(101, "S100", "Saville Hall - Engineering Lab")},
			{new Room(151, "PP414", "President's Place - Computer Lab")},
			{new Room(3051, "PP411", "President's Place - Computer Lab")},
			{new Room(3101, "PP412", "Faculty Offices")},
		});
	}
	
	public RoomServicesTest(Room expected) {
		super();
		this.expected = expected;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAddRoom() {
		// add dummy fixed room
		boolean success = roomServices.addRoom(expected2);
		// check if added successfully
		assertTrue(success);
		// need to copy the id from database since it autoincrements
		Room actual = roomServices.getRoomByName(expected2.getName());
		expected2.setId(actual.getId());
		// check content of data
		assertEquals(expected2, actual);
		// delete room on exit
		roomServices.deleteRoom(actual);
	}
	
	@Test
	public final void testDeleteRoom() {
		// add dummy fixed room+verify
		boolean success = roomServices.addRoom(expected2);
		assertTrue(success);
		//  get room (id) due to autoincrement
		Room copy = roomServices.getRoomByName(expected2.getName());
		// remove room (thru id)+verify
		success = roomServices.deleteRoom(copy);
		assertTrue(success);
		// verify thru id search, verify null result
		Room actual = roomServices.getRoomById(copy.getId());
		assertNull(actual);
	}
	
	@Test
	public final void testGetAllRooms() {
		//get all rooms from database
		List<Room> actualList = roomServices.getAllRooms();
		// get all data in JUnit test

		List<Room> expectedList = new ArrayList<Room>();
		expectedList.addAll(params());
		
		System.out.println("expectedList");
		System.out.println(expectedList);
		System.out.println("actualList");
		System.out.println(actualList);
		
		// see if JUnit test value is in room list
		assertTrue(actualList.contains(expected));
	}
	
	@Test
	public final void testGetRoomById() {
		// get room by id + verify
		Room actual = roomServices.getRoomById(expected.getId());
		assertEquals(expected, actual);
	}

	@Test
	public final void testGetRoomByName() {
		// get room by id + verify
		Room actual = roomServices.getRoomByName(expected.getName());
		assertEquals(expected, actual);
	}

	@Test
	public final void testUpdateRoom() {
		// get room by name + verify
		// add dummy fixed room
		boolean success = roomServices.addRoom(expected2);
		// check if added successfully
		assertTrue(success);
		// need to copy the id from database since it autoincrements
		Room copy = roomServices.getRoomByName(expected2.getName());
		expected3.setId(copy.getId());
		// set new data
		copy.setName(expected3.getName());
		copy.setLocation(expected3.getLocation());
		// update database
		success = roomServices.updateRoom(copy);
		assertTrue(success);
		// need to copy the current version from database
		Room actual = roomServices.getRoomById(expected2.getId());
		// check content of data
		assertEquals(expected3, actual);
		// delete room on exit
		roomServices.deleteRoom(actual);
	}
}
