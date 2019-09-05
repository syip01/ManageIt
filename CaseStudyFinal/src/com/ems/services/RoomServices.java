package com.ems.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.ems.DAOI.RoomDAO;
import com.ems.entities.Room;

public class RoomServices implements RoomDAO {
	private static final String projectName = "CaseStudyFinal";
	@Override
	public List<Room> getAllRooms()
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT r FROM Room r", name="queryAllRooms"),
		Query query = entityManager.createNamedQuery("queryAllRooms");
		List<Room> roomList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();		
		
		return roomList;
	}
	
	@Override
	public Room getRoomById(int pId)
	{
		Room foundRoom = null;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			foundRoom = entityManager.find(Room.class, pId);
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

		return foundRoom;
	}
		
	@Override
	public boolean addRoom(Room room){
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			entityManager.persist(room);
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
	public boolean updateRoom(Room room) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			Room foundRoom = entityManager.find(Room.class, room.getId());
			foundRoom.setName(room.getName());
			foundRoom.setLocation(room.getLocation());
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
	public boolean deleteRoom(Room room) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			Room foundRoom = entityManager.find(Room.class, room.getId());
			entityManager.remove(foundRoom);
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
	public Room getRoomByName(String name)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT e FROM EventAdmin e WHERE e.username=:username", name="queryAdminUsername"),
		Query query = entityManager.createNamedQuery("queryRoom");
		query.setParameter("name", name);
		List<Room> roomList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		
		return roomList.isEmpty() ? null : roomList.get(0);
	}
}
