package lv.autoosta.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.autoosta.model.BusCategory;
import lv.autoosta.model.Driver;
import lv.autoosta.repos.IDriverRepo;
import lv.autoosta.services.IDriverCRUDService;

@Service
public class CRUDDriverServiceImpl implements IDriverCRUDService {
	
	@Autowired
	private IDriverRepo driverRepo;

	@Override
	public void insertNewDriver(String name, String surname, Set<BusCategory> category) throws Exception {
		if (driverRepo.existsByNameAndSurname(name, surname)) {
			Driver temp = driverRepo.findByNameAndSurname(name, surname);
			driverRepo.save(temp);
		} else {
			Driver newDriver = new Driver(name, surname, category);
			driverRepo.save(newDriver);
		}
	}

	@Override
	public ArrayList<Driver> selectAllDrivers() {
		return (ArrayList<Driver>) driverRepo.findAll();
	}

	@Override
	public Driver selectDriverByID(long id) throws Exception {
		if (id > 0) {
			if (driverRepo.existsById(id)) {
				Driver temp = driverRepo.findById(id).get();
				return temp;
			} else {
				throw new Exception("Product with the specified ID not found!");
			}
		} else {
			throw new Exception("Incorrect id!");
		}
	}

	@Override
	public void updateDriverByID(long id, String name, String surname, Set<BusCategory> category) throws Exception {
		if (id > 0) {
			if (driverRepo.existsById(id)) {
				Driver temp = driverRepo.findById(id).get();
				temp.setName(name);;
				temp.setSurname(surname);;
				temp.setCategories(category);
				driverRepo.save(temp);
			} else {
				throw new Exception("There is no product with the specified ID!");
			}
		} else {
			throw new Exception("Incorrect id!");
		}
	}

	@Override
	public void deleteDriverByID(long id) throws Exception {
		if (driverRepo.existsById(id)) {
			driverRepo.deleteById(id);
		} else {
			throw new Exception("Product with the specified ID not found!!");
		}
	}
	

}
