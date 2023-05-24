package lv.autoosta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.autoosta.model.Driver;

public interface IDriverRepo extends CrudRepository<Driver, Long> {
	boolean existsByNameAndSurname(String inputName, String inputSurname);
	Driver findByNameAndSurname(String inputName, String inputSurname);
	Driver findByIdd(long idd);
}
