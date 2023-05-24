package lv.autoosta.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.autoosta.model.BusCategory;
import lv.autoosta.model.Driver;
import lv.autoosta.services.IDriverCRUDService;

@Controller
public class DriverController {
	
	@Autowired
	private IDriverCRUDService CRUDservice;
	
	@GetMapping("/error")
	public String getErrorFunc(Model model) {
		model.addAttribute("packetError", "Wrong ID");
		return "error-page";
	}
	
	@GetMapping("/driver/showAll") // localhost:8080/driver/showAll
	public String getShowAllDriversFunc(Model model) {
		model.addAttribute("message", CRUDservice.selectAllDrivers());
		return "driver-all-page"; 
	}
	
	@GetMapping("/driver/showAll/{id}") // localhost:8080/driver/showAll/2
	public String getShowAllDriversByIDFunc(@PathVariable("id") long id, Model model) {
		try {
			Driver driver = CRUDservice.selectDriverByID(id);
			model.addAttribute("message", driver);
			return "driver-one-page";	
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/driver/remove/{id}") // localhost:8080/driver/remove/2
	public String getRemoveDriverFunc(@PathVariable("id") long id, Model model) {
		try {
			CRUDservice.deleteDriverByID(id);
			model.addAttribute("message", CRUDservice.selectAllDrivers());
			return "driver-all-page";
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}
	
	// https://stackoverflow.com/questions/65725197/get-checkbox-values-as-array-from-html-form-in-spring
	@GetMapping
	public String getOrderFormFunc(@Valid Driver driver, @RequestParam(value = "selected", required = true) Set<BusCategory> selected) {
		driver.setCategories(selected);
		return "driver-all-page";
	}
	
	@GetMapping("/driver/addNew") // localhost:8080/driver/addNew
	public String getAddDriverFunc(Model model) {
		model.addAttribute("driver", new Driver());
		return "driver-add-page";
	}
	
	@PostMapping("/driver/addNew") // localhost:8080/driver/addNew
	public String postAddDriverFunc(@Valid Driver driver, BindingResult result) {
		
		if (!result.hasErrors()) {
			try {
				CRUDservice.insertNewDriver(driver.getName(), driver.getSurname(), driver.getCategories());
				return "redirect:/driver/showAll";
			} catch (Exception e) {
				return "redirect:/error";
			}
		} else {
			return "driver-add-page";
		}
	}
	
	@GetMapping("/driver/update/{id}") // localhost:8080/driver/update/2
	public String getUpdateDriverFunc(@PathVariable("id") long id, Model model) {
		try {
			Driver driver = CRUDservice.selectDriverByID(id);
			model.addAttribute("driver", driver);
			return "driver-update-page";
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/driver/update/{id}") // localhost:8080/driver/update/2
	public String postUpdateDriverFunc(@PathVariable("id") long id, @Valid Driver driver, BindingResult result) {
		
		if (!result.hasErrors()) {
			try {
				CRUDservice.updateDriverByID(id, driver.getName(), driver.getSurname(), driver.getCategories());
				return "redirect:/driver-all-page";
			} catch (Exception e) {
				return "redirect:/error";
			}
		} else {
			return "driver-update-page";
		}
	}

}
