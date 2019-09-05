package com.ems.testing;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.ems.entities.*;
import com.ems.services.GroupServices;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupServicesTest {
	GroupServices groupServices = new GroupServices();
	Group expected;
	Group expected2 = new Group(777, "Fake Club", "fakeclub@gmail.com", new ArrayList<EmailInfo>(), new ArrayList<User>());
	Group expected3 = new Group(789, "Real Club", "realclub@gmail.com", new ArrayList<EmailInfo>(), new ArrayList<User>());

	@Parameterized.Parameters
	public static Collection params()
	{
		return Arrays.asList(new Object [][]{
			{new Group(101, "Computer Club", "computerclub@gmail.com", new ArrayList<EmailInfo>(), new ArrayList<User>())},
			{new Group(151, "Chess Club", "chessclub@gmail.com", new ArrayList<EmailInfo>(), new ArrayList<User>())},
		});
	}
	
	public GroupServicesTest(Group expected) {
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
	public final void testAddGroup() {
		// add dummy fixed group
		boolean success = groupServices.addGroup(expected2);
		// check if added successfully
		assertTrue(success);
		// need to copy the id from database since it autoincrements
		Group actual = groupServices.getGroupByName(expected2.getName());
		expected2.setId(actual.getId());
		// check content of data
		assertEquals(expected2, actual);
		// delete room on exit
		groupServices.deleteGroup(actual);
	}
	
	@Test
	public final void testDeleteGroup() {
		// add dummy fixed group+verify
		boolean success = groupServices.addGroup(expected2);
		assertTrue(success);
		//  get group (id) due to autoincrement
		Group copy = groupServices.getGroupByName(expected2.getName());
		// remove group (thru id)+verify
		success = groupServices.deleteGroup(copy);
		assertTrue(success);
		// verify thru id search, verify null result
		Group actual = groupServices.getGroupById(copy.getId());
		assertNull(actual);
	}

	@Test
	public final void testGetAllGroups() {
		//get all groups from database
		List<Group> actualList = groupServices.getAllGroups();
		// get all data in JUnit test

		//List<Room> expectedList = new ArrayList<Room>(Arrays.asList(roomList));
		//List<Room> expectedList = (List<Room>) params();
		List<Group> expectedList = new ArrayList<Group>();
		expectedList.addAll(params());
		
		System.out.println("expectedList");
		System.out.println(expectedList);
		System.out.println("actualList");
		System.out.println(actualList);
		
		// see if JUnit test value is in group list
		//assertTrue(expectedList.contains(actualList) && expectedList.size() == actualList.size());
		assertTrue(actualList.contains(expected));
	}

	@Test
	public final void testGetGroupById() {
		// get group by id + verify
		Group actual = groupServices.getGroupById(expected.getId());
		assertEquals(expected, actual);
	}
	
	@Test
	public final void testGetGroupByName() {
		// get group by id + verify
		Group actual = groupServices.getGroupByName(expected.getName());
		assertEquals(expected, actual);
	}

	@Test
	public final void testUpdateGroup() {
		// get group by name + verify
		// add dummy fixed group
		boolean success = groupServices.addGroup(expected2);
		// check if added successfully
		assertTrue(success);
		// need to copy the id from database since it autoincrements
		Group copy = groupServices.getGroupByName(expected2.getName());
		expected3.setId(copy.getId());
		// set new data
		copy.setName(expected3.getName());
		copy.setEmail(expected3.getEmail());
		// update database
		success = groupServices.updateGroup(copy);
		assertTrue(success);
		// need to copy the current version from database
		Group actual = groupServices.getGroupById(expected2.getId());
		// check content of data
		assertEquals(expected3, actual);
		// delete room on exit
		groupServices.deleteGroup(actual);
	}
}
