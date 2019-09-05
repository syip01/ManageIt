package com.ems.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.ems.DAOI.GroupDAO;
import com.ems.entities.Group;

public class GroupServices implements GroupDAO {
	private static final String projectName = "CaseStudyFinal";
	@Override
	public List<Group> getAllGroups()
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT g FROM Group g", name="queryAllGroups"),
		Query query = entityManager.createNamedQuery("queryAllGroups");
		List<Group> groupList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();		
		
		return groupList;
	}
	
	@Override
	public Group getGroupById(int pId)
	{
		Group foundGroup = null;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			foundGroup = entityManager.find(Group.class, pId);
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

		return foundGroup;
	}
		
	@Override
	public boolean addGroup(Group group){
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			entityManager.persist(group);
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
	public boolean updateGroup(Group group) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			Group foundGroup = entityManager.find(Group.class, group.getId());
			foundGroup.setName(group.getName());
			foundGroup.setEmail(group.getEmail());
			foundGroup.setMailingList(group.getMailingList());
			foundGroup.setUserList(group.getUserList());
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
	public boolean deleteGroup(Group group) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try
		{
			entityManager.getTransaction().begin();
			Group foundGroup = entityManager.find(Group.class, group.getId());
			entityManager.remove(foundGroup);
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
	public Group getGroupByName(String name)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(projectName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//@NamedQuery (query="SELECT g FROM Group g where g.name=:name", name="queryGroup"),
		Query query = entityManager.createNamedQuery("queryGroup");
		query.setParameter("name", name);
		List<Group> groupList = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		
		return groupList.isEmpty() ? null : groupList.get(0);
	}

}
