package lv.venta.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Grade;

public interface IGradeRepo extends CrudRepository<Grade, Long> {

	ArrayList<Grade> findByStudentIds(long id);

	ArrayList<Grade> findByCourseIdd(long id);

}
