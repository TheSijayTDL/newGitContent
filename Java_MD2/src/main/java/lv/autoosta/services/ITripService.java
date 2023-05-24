package lv.autoosta.services;

import java.util.ArrayList;

import lv.autoosta.model.Trip;

public interface ITripService {
	
	ArrayList<Trip> selectAllTrips();
	
	ArrayList<Trip> selectAllTripsToday();
	
	ArrayList<Trip> selectAllTripsByDriverId(long idd) throws Exception;
	
	Trip changeTripDriverByDriverId(long idd, long idtr) throws Exception;

	ArrayList<Trip> selectAllByCitiesTitle(String title);
}
