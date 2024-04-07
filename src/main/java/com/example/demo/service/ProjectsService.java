package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Projects;

public interface ProjectsService {

	List<Projects> findProjectsList();
	
	Projects findProjectsById(int theId);
	
	Projects saveProjectsList(Projects projects);
	
	void deleteProjectsById(int theId);
	
}
