package com.ems.testing;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.ems.entities.*;
import com.ems.services.EventUserServices;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventUserServicesTest {
	EventUserServices userServices = new EventUserServices();
	EventUser expected;
	EventUser expected2 = new EventUser(123, "user #123", "user123", "user123@email.com", "user123", new ArrayList<Group>());
	EventUser expected3 = new EventUser(456, "user #456", "user456", "user456@email.com", "user456", new ArrayList<Group>());
	
	@Parameterized.Parameters
	public static Collection params()
	{
		return Arrays.asList(new Object [][]{
			{new EventUser(101, "lala1", "lala", "lala@email.com", "lala", new ArrayList<Group>())},
			{new EventUser(151, "lala22", "lala2", "lala22@email.com", "lala", new ArrayList<Group>())},
			{new EventUser(201, "lala333", "lala3", "lala333@email.com", "lala", new ArrayList<Group>())},
		});
	}
		
	public EventUserServicesTest(EventUser expected) {
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
	public final void testAddEventUser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddGroupById() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void testDeleteEventUser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllEventUsers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllUserGroups() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetEventUserById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetEventUserByUsername() {
		fail("Not yet implemented"); // TODO		
	}

	@Test
	public final void testIsValidLogin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRemoveGroupById() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void testUpdateEventUser() {
		fail("Not yet implemented"); // TODO
	}
}
