package lv.autoosta.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.autoosta.model.Driver;
import lv.autoosta.model.Trip;
import lv.autoosta.repos.IDriverRepo;
import lv.autoosta.repos.ITripRepo;
import lv.autoosta.services.ITripService;

@Service
public class TripServiceImpl implements ITripService {

	@Autowired
	private ITripRepo tripRepo;
	
	@Autowired
	private IDriverRepo driverRepo;
	
	@Override
	public ArrayList<Trip> selectAllByCitiesTitle(String title) {
		ArrayList<Trip> filteredResults = tripRepo.findAllByCitiesTitle(title);
		return filteredResults;
	}
	
	@Override
	public ArrayList<Trip> selectAllTrips() {
		return (ArrayList<Trip>) tripRepo.findAll();
	}

	@Override
	public ArrayList<Trip> selectAllTripsToday() {
		ArrayList<Trip> filteredResults = selectAllTrips();
		for (int i = 0; i < filteredResults.size(); i++) {
			if (filteredResults.get(i).getTimeInfo().getDayOfMonth() != LocalDateTime.now().getDayOfMonth()) {
				filteredResults.remove(i);
			} else {
				continue;
			}
		}
		return filteredResults;
	}

	@Override
	public ArrayList<Trip> selectAllTripsByDriverId(long idd) throws Exception {
		if (idd > 0) {
			ArrayList<Trip> filteredResults = tripRepo.findAllByDriverIdd(idd);
			return filteredResults;
		} else {
			throw new Exception("Incorrect id!");
		}
	}

	@Override
	public Trip changeTripDriverByDriverId(long idd, long idtr) throws Exception {
		if (idd > 0 && idtr > 0 && driverRepo.existsById(idd) && tripRepo.existsById(idtr)) {
			Trip tempTrip = tripRepo.findByIdtr(idtr);
			tempTrip.setDriver(driverRepo.findByIdd(idd));
			tripRepo.save(tempTrip);
			return tempTrip;
		} else {
			throw new Exception("Incorrect id!");
		}
	}


}
