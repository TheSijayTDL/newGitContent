package lv.autoosta.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.autoosta.model.Trip;

public interface ITripRepo extends CrudRepository<Trip, Long> {
	ArrayList<Trip> findAllByCitiesTitle(String title);
	ArrayList<Trip> findAllByDriverIdd(long idd);
	Trip findByIdtr(long idtr);
}
