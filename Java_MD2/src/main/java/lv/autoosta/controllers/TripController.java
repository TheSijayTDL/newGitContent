package lv.autoosta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.autoosta.services.ICityService;
import lv.autoosta.services.ITripService;

@Controller
public class TripController {
	
	@Autowired
	private ITripService tripService;
	
	@Autowired
	private ICityService cityService;
	
	@GetMapping("/trip/showAll") // localhost:8080/trip/showAll
	public String getSelectAllTripsFunc(Model model) {
		model.addAttribute("cities", tripService.selectAllTrips());
		return "trip-all-page"; 
	}
	
	@GetMapping("/trip/showAll/city/{title}") // localhost:8080/trip/showAll/city/ventspils
	public String getSelectAllTripsByCityTitleFunc(@PathVariable("title") String title, Model model) {
		try {
			model.addAttribute("cities", tripService.selectAllByCitiesTitle(title));
			return "trip-all-page";
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/trip/showAll/driver/{id}") // localhost:8080/trip/showAll/driver/1
	public String getSelectAllTripsByCityTitleFunc(@PathVariable("id") long id, Model model) {
		try {
			model.addAttribute("cities", tripService.selectAllTripsByDriverId(id));
			return "trip-all-page";
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/trip/showAll/today") // localhost:8080/trip/showAll/today
	public String getSelectAllTripsTodayFunc(Model model) {
		model.addAttribute("cities", tripService.selectAllTripsToday());
		return "trip-all-page"; 
	}
	
	@GetMapping("/trip/changeDriver/{idtr}/{idd}") // localhost:8080/trip/changeDriver/1/2
	public String getChangeDriverByIdFunc(@PathVariable("idd") long idd, @PathVariable("idtr") long idtr, Model model) {
		try {
			model.addAttribute("cities", tripService.changeTripDriverByDriverId(idd, idtr));
			return "trip-all-page"; 
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}

}
