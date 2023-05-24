package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Professor;

public interface IProfessorRepo extends CrudRepository<Professor, Long> {

}
