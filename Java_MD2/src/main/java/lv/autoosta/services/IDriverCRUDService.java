package lv.autoosta.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import lv.autoosta.model.BusCategory;
import lv.autoosta.model.Driver;

public interface IDriverCRUDService {
	// CRUD
	
	// C - Create
	void insertNewDriver(String name, String surname, Set<BusCategory> category) throws Exception;
	
	// R - Retrieve All
	ArrayList<Driver> selectAllDrivers();
	
	// R - Retrieve by ID
	Driver selectDriverByID(long id) throws Exception;
	
	// U - Update
	void updateDriverByID(long id, String name, String surname, Set<BusCategory> category) throws Exception;
	
	// D - Delete
	void deleteDriverByID(long id) throws Exception;
}
