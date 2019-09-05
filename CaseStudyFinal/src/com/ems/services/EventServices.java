package com.ems.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.ems.DAOI.EventDAO;
import com.ems.entities.Event;
import com.ems.entities.Room;
import com.ems.entities.Group;
import com.ems.entities.EventAdmin;

public class EventServices implements EventDAO {
	private static final String projectName = "CaseStudyFinal";
	@Override
	public List<Event> getAllEvents()
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT e FROM Event e", name="queryAllEvents"),
		Query query = entityManager.createNamedQuery("queryAllEvents");
		List<Event> eventList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();		
		
		return eventList;
	}
	
	@Override
	public Event getEventById(int pId)
	{
		Event foundEvent = null;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			foundEvent = entityManager.find(Event.class, pId);
		}
		catch(PersistenceException e)
		{
			e.getMessage();
		}
		finally
		{
			entityManager.close();
			entityManagerFactory.close();
		}

		return foundEvent;
	}
		
	@Override
	public boolean addEvent(Event event){
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Room foundRoom = null;
		Group foundGroup = null;
		EventAdmin foundAdmin = null;
		
		try
		{
			foundRoom = entityManager.find(Room.class, event.getRoom().getId());
			foundGroup = entityManager.find(Group.class, event.getGroup().getId());
			foundAdmin = entityManager.find(EventAdmin.class, event.getAdmin().getId()); 
			
			//System.out.println("Tried to find stuff");
			
			//if (foundRoom == null || foundGroup == null)
			//	throw new NullPointerException();
			
			//System.out.println("No nulls");
			
			//System.out.println(foundRoom);
			//System.out.println(foundGroup);
			//System.out.println(foundAdmin);

			event.setRoom(foundRoom);
			event.setGroup(foundGroup);
			event.setAdmin(foundAdmin);
			//System.out.println(event);

			entityManager.getTransaction().begin();			
			entityManager.persist(event);
			entityManager.getTransaction().commit();
		}
		catch(PersistenceException e)
		{
			System.out.println("PersistenceException");
			e.getMessage();
			e.getStackTrace();
			result = false;
		}
		// should be only be triggered by users
		catch(NullPointerException e)
		{
			System.out.println("EventAdd: NullPointerException");
			event.setRoom(foundRoom);
			event.setGroup(foundGroup);
			event.setAdmin(foundAdmin);
			entityManager.getTransaction().begin();			
			entityManager.persist(event);
			entityManager.getTransaction().commit();
		}
		finally
		{
			entityManager.close();
			entityManagerFactory.close();
		}

		return result;
	}
	
	@Override
	public boolean updateEvent(Event event) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Event foundEvent = null;
		Room foundRoom = null;
		Group foundGroup = null;
		EventAdmin foundAdmin = null;
		
		try
		{
			entityManager.getTransaction().begin();
			foundEvent = entityManager.find(Event.class, event.getId());
			foundRoom = entityManager.find(Room.class, event.getRoom().getId());
			foundGroup = entityManager.find(Group.class, event.getGroup().getId());
			foundAdmin = entityManager.find(EventAdmin.class, event.getAdmin().getId()); 

			//if (foundEvent == null || foundRoom == null || foundGroup == null)
			//	throw new NullPointerException();

			foundEvent.setStartTime(event.getStartTime());
			foundEvent.setEndTime(event.getEndTime());
			foundEvent.setStatus(event.getStatus());
			
			foundEvent.setRoom(foundRoom);
			foundEvent.setGroup(foundGroup);
			foundEvent.setAdmin(foundAdmin);
			
			entityManager.getTransaction().commit();
		}
		catch(PersistenceException e)
		{
			e.getMessage();
			result = false;
			foundEvent = null;
		}
		// should be only be triggered by users		
		catch(NullPointerException e)
		{
			System.out.println("EventUpdate: NullPointerException");
			foundEvent.setStartTime(event.getStartTime());
			foundEvent.setEndTime(event.getEndTime());
			foundEvent.setStatus(event.getStatus());
			
			foundEvent.setRoom(foundRoom);
			foundEvent.setGroup(foundGroup);
			//entityManager.persist(event);
			entityManager.getTransaction().commit();
		}
		finally
		{
			entityManager.close();
			entityManagerFactory.close();
		}

		return result;
	}
	
	@Override
	public boolean deleteEvent(Event event) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			Event foundEvent = entityManager.find(Event.class, event.getId());
			entityManager.remove(foundEvent);
			entityManager.getTransaction().commit();
		}
		catch(PersistenceException e)
		{
			e.getMessage();
			result = false;
		}
		finally
		{
			entityManager.close();
			entityManagerFactory.close();
		}

		return result;
	}

	@Override
	public List<Event> getAllEventsFromGroupId(int pId)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT e FROM Event e WHERE e.group.id=:id", name="queryAllEventsByGroupId"),
		Query query = entityManager.createNamedQuery("queryAllEventsByGroupId");
		query.setParameter("id", pId);
		List<Event> eventList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();		
		
		return eventList;
	}
}
