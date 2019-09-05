package com.ems.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.ems.DAOI.EventUserDAO;
import com.ems.entities.EventUser;
import com.ems.entities.Group;

public class EventUserServices implements EventUserDAO {
	private static final String projectName = "CaseStudyFinal";
	@Override
	public List<EventUser> getAllEventUsers()
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT e FROM EventUser e", name="queryAllEventUsers"),
		Query query = entityManager.createNamedQuery("queryAllEventUsers");
		List<EventUser> userList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();		
		
		return userList;
	}
	
	@Override
	public EventUser getEventUserById(int pId)
	{
		EventUser foundUser = null;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			foundUser = entityManager.find(EventUser.class, pId);
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

		return foundUser;
	}
		
	@Override
	public boolean addEventUser(EventUser user){
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			entityManager.persist(user);
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
	public boolean updateEventUser(EventUser user) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			EventUser foundUser = entityManager.find(EventUser.class, user.getId());
			foundUser.setName(user.getName());
			foundUser.setEmail(user.getEmail());
			foundUser.setPassword(user.getPassword());
			foundUser.setUsername(user.getUsername());
			foundUser.setGroupList(user.getGroupList());
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
	public boolean deleteEventUser(EventUser eventUser) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			EventUser foundUser = entityManager.find(EventUser.class, eventUser.getId());
			entityManager.remove(foundUser);
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
	public boolean isValidLogin(String username, String password)
	{
		EventUser user = getEventUserByUsername(username);
		return (user != null && user.getPassword().equals(password));
	}
	
	@Override
	public EventUser getEventUserByUsername(String username)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT e FROM EventUser e WHERE e.username=:username", name="queryUserUsername"),
		Query query = entityManager.createNamedQuery("queryUserUsername");
		query.setParameter("username", username);
		List<EventUser> userList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		
		return userList.isEmpty() ? null : userList.get(0);
	}
	
	@Override
	public boolean addGroupById(EventUser user, int pId)
	{
		GroupServices groupServices = new GroupServices();
		Group foundGroup = groupServices.getGroupById(pId);
		boolean updateSuccess = false;
		
		if(foundGroup != null)
		{
			List<Group> groupList = user.getGroupList();
			groupList.add(foundGroup);
			user.setGroupList(groupList);
			updateSuccess = updateEventUser(user);
		}
		
		return updateSuccess;
	}
	
	@Override
	public boolean removeGroupById(EventUser user, int pId)
	{
		GroupServices groupServices = new GroupServices();
		Group foundGroup = groupServices.getGroupById(pId);
		boolean updateSuccess = false;

		if(foundGroup != null)
		{
			List<Group> groupList = user.getGroupList();
			boolean removeSuccess = groupList.remove(foundGroup);
			user.setGroupList(groupList);
			updateSuccess = updateEventUser(user) && removeSuccess;
		}
		
		return updateSuccess;
	}
	
	@Override
	public List<Group> getAllUserGroups(int pId)
	{
		EventUser user = getEventUserById(pId);
		return user.getGroupList();
	}
}
