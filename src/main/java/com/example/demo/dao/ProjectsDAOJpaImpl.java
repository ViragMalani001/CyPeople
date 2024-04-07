package com.example.demo.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.example.demo.entity.Projects;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ProjectsDAOJpaImpl implements ProjectsDAO {
	
	private EntityManager entityManager;

	public ProjectsDAOJpaImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Projects> findProjectsList() {
		
		TypedQuery<Projects> theQuery = entityManager.createQuery("FROM Projects", Projects.class);
		List<Projects> findProjectsList = theQuery.getResultList();
		return findProjectsList;
	}

	@Override
	public Projects findProjectsById(int theId) {
		
		Projects findProjectsById = entityManager.find(Projects.class, theId);
		return findProjectsById;
	}

	@Override
	public Projects saveProjectsList(Projects projects) {
		
		Projects saveProjectsList = entityManager.merge(projects);
		return saveProjectsList;
	}

	@Override
	public void deleteProjectsById(int theId) {

		Projects deleteProjectsById = entityManager.find(Projects.class, theId);
		entityManager.remove(deleteProjectsById);
	}

	
}
