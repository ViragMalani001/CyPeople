package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ProjectsDAO;
import com.example.demo.entity.Projects;

import jakarta.transaction.Transactional;

@Service
public class ProjectsServiceImpl implements ProjectsService {
	
	private ProjectsDAO projectsDAO;

	public ProjectsServiceImpl(ProjectsDAO projectsDAO) {
		super();
		this.projectsDAO = projectsDAO;
	}

	@Override
	public List<Projects> findProjectsList() {

		return this.projectsDAO.findProjectsList();
	}

	@Override
	public Projects findProjectsById(int theId) {
		
		return this.projectsDAO.findProjectsById(theId);
	}

	@Transactional
	@Override
	public Projects saveProjectsList(Projects projects) {
		
		return this.projectsDAO.saveProjectsList(projects);
	}

	@Transactional
	@Override
	public void deleteProjectsById(int theId) {
		
		this.projectsDAO.deleteProjectsById(theId);
		
	}

}
