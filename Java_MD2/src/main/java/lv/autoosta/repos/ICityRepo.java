package lv.autoosta.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.autoosta.model.City;
import lv.autoosta.model.Trip;

public interface ICityRepo extends CrudRepository<City, Long> {

}
