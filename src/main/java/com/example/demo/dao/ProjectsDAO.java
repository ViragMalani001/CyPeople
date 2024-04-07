package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Projects;

public interface ProjectsDAO {

	List<Projects> findProjectsList();
	
	Projects findProjectsById(int theId);
	
	Projects saveProjectsList(Projects projects);
	
	void deleteProjectsById(int theId);
	
}
