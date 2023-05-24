package lv.venta;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.models.Course;
import lv.venta.models.Degree;
import lv.venta.models.Grade;
import lv.venta.models.Professor;
import lv.venta.models.Student;
import lv.venta.repos.ICourseRepo;
import lv.venta.repos.IGradeRepo;
import lv.venta.repos.IProfessorRepo;
import lv.venta.repos.IStudentRepo;

@SpringBootApplication
public class JavaSem6Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaSem6Application.class, args);
	}

	@Bean
	public CommandLineRunner testModel(IProfessorRepo prRepo, IStudentRepo stRepo, IGradeRepo grRepo, ICourseRepo coRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Professor p1 = new Professor("Juris", "Zagars", Degree.phd);
				Professor p2 = new Professor("Dmitrijs", "Smirnovs", Degree.phd);
				Professor p3 = new Professor("Karina", "Skirmante", Degree.mg);
				prRepo.save(p1);
				prRepo.save(p2);
				prRepo.save(p3);
				
				Student s1 = new Student("Janis", "Berzins");
				Student s2 = new Student("Baiba", "Kalnina");
				stRepo.save(s1);
				stRepo.save(s2);
				
				Course c1 = new Course("Haosa teorija", 4, new ArrayList<>(Arrays.asList(p1)));
				Course c2 = new Course("Ekonomika", 2, new ArrayList<>(Arrays.asList(p2)));
				Course c3 = new Course("Dabaszinatnu pamati", 2, new ArrayList<>(Arrays.asList(p1)));
				Course c4 = new Course("Java", 4, new ArrayList<>(Arrays.asList(p2, p3)));
				coRepo.save(c1);
				coRepo.save(c2);
				coRepo.save(c3);
				coRepo.save(c4);
				
				c1.addProfessor(p1);
				c2.addProfessor(p2);
				c3.addProfessor(p1);
				c4.addProfessor(p2);
				c4.addProfessor(p3);
				
				coRepo.save(c1);
				coRepo.save(c2);
				coRepo.save(c3);
				coRepo.save(c4);
				
				//Grade g1 = new Grade(5, s1, c1);
				grRepo.save(new Grade(5, s1, c1));
				grRepo.save(new Grade(7, s1, c2));
				grRepo.save(new Grade(10, s2, c1));
				grRepo.save(new Grade(8, s2, c2));
			}
		};
	}
}
