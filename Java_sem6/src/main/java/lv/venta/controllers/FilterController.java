package lv.venta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.services.IFilteringService;

@Controller
public class FilterController {
	
	@Autowired
	private IFilteringService filterService;
	
	@GetMapping("/info/showAllStudents") // localhost:8080/info/showAllStudents
	public String getAllStudents(Model model) {
		model.addAttribute("students", filterService.retrieveAllStudents());
		return "all-students-page";
	}
	
	@GetMapping("/info/showAllProfessors") // localhost:8080/info/showAllProfessors
	public String getAllProfessors(Model model) {
		model.addAttribute("professors", filterService.retrieveAllProfessors());
		return "all-professors-page";
	}
	
	@GetMapping("/info/showAllGrades") // localhost:8080/info/showAllGrades
	public String getAllGrades(Model model) {
		model.addAttribute("grades", filterService.retrieveAllGrades());
		return "all-grades-page";
	}
	
	@GetMapping("/info/showAllCourses") // localhost:8080/info/showAllCourses
	public String getAllCourses(Model model) {
		model.addAttribute("courses", filterService.retrieveAllCourses());
		return "all-courses-page";
	}
}
