package com.ems.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.ems.DAOI.EventAdminDAO;
import com.ems.entities.EventAdmin;

public class EventAdminServices implements EventAdminDAO {
	private static final String projectName = "CaseStudyFinal";
	@Override
	public List<EventAdmin> getAllEventAdmins()
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT e FROM EventAdmin e", name="queryAllEventAdmins"),
		Query query = entityManager.createNamedQuery("queryAllEventAdmins");
		List<EventAdmin> adminList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();		
		
		return adminList;
	}
	
	@Override
	public EventAdmin getEventAdminById(int pId)
	{
		EventAdmin foundAdmin = null;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			foundAdmin = entityManager.find(EventAdmin.class, pId);
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

		return foundAdmin;
	}
		
	@Override
	public boolean addEventAdmin(EventAdmin admin){
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			entityManager.persist(admin);
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
	public boolean updateEventAdmin(EventAdmin admin) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			EventAdmin foundAdmin = entityManager.find(EventAdmin.class, admin.getId());
			foundAdmin.setName(admin.getName());
			foundAdmin.setEmail(admin.getEmail());
			foundAdmin.setPassword(admin.getPassword());
			foundAdmin.setUsername(admin.getUsername());
			foundAdmin.setGroupList(admin.getGroupList());
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
	public boolean deleteEventAdmin(EventAdmin admin) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			EventAdmin foundAdmin = entityManager.find(EventAdmin.class, admin.getId());
			entityManager.remove(foundAdmin);
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
		EventAdmin admin = getEventAdminByUsername(username);
		return (admin != null && admin.getPassword().equals(password));
	}
	
	@Override
	public EventAdmin getEventAdminByUsername(String username)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT e FROM EventAdmin e WHERE e.username=:username", name="queryAdminUsername"),
		Query query = entityManager.createNamedQuery("queryAdminUsername");
		query.setParameter("username", username);
		List<EventAdmin> adminList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		
		return adminList.isEmpty() ? null : adminList.get(0);
	}
}
