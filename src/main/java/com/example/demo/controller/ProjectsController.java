package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Projects;
import com.example.demo.service.ProjectsService;

@Controller
public class ProjectsController {
	
	private ProjectsService projectsService;
	
	public ProjectsController(ProjectsService projectsService) {
		super();
		this.projectsService = projectsService;
	}

	
	@GetMapping("/projects")
	public String showProjectsPage() {
		return "/projects/projects";
	}
	
	@GetMapping("/projects-add")
	public String projectsAddPage(Model theModel){
		
		Projects projects = new Projects();
		theModel.addAttribute("projects",projects);
		return "/projects/projects-add";		
	}
	
	@PostMapping("/projects-add")
	public String projectsAddPage(@ModelAttribute("projects") Projects projects){

		this.projectsService.saveProjectsList(projects);
		return "redirect:/projects-list";
	}
	
	
	@GetMapping("/projects-list")
	public String showProjectsListPage(Model theModel){
		
		List<Projects> projects = this.projectsService.findProjectsList();
		theModel.addAttribute("projects",projects);
		return "/projects/projects-list";
	}
	
	@GetMapping("/projects-update")
	public String projectUpdatePage(@RequestParam("projectId") int theId, Model theModel) {
				
		Projects project = this.projectsService.findProjectsById(theId);
		theModel.addAttribute("projects",project);
		return "projects/projects-add";	
	}
	
	@GetMapping("/projects-delete")
	public String empDelete(@RequestParam("projectId") int theId) {
		
		this.projectsService.deleteProjectsById(theId);
		return "redirect:/projects-list";
	}
	
	
	@GetMapping("/projects-detail")
	public String showProjectsDetailPage(Model theModel){
		
		List<Projects> projects = this.projectsService.findProjectsList();
		theModel.addAttribute("projects",projects);
		return "/projects/projects-detail";
	}
}
