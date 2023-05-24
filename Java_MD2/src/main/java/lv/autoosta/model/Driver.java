package lv.autoosta.model;

import java.util.Collection;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name = "driver_table") // DB table
@Entity
public class Driver {
	@Column(name = "Name")
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+")
	@Size(min = 3, max = 35)
	private String name;
	
	@Column(name = "Surname")
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+")
	@Size(min = 3, max = 35)
	private String surname;
	
	@Column(name = "Categories")
	@NotNull
	@Enumerated(EnumType.STRING)
	private Set<BusCategory> categories;
		
	@Column(name = "Idd")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idd;
	
	@OneToMany(mappedBy = "driver")
	private Collection<Trip> trips;
	
	public Driver() {
		this.name = "Test";
		this.surname = "Test";
	}
	
	public Driver(String name, String surname, Set<BusCategory> category) {
		this.name = name;
		this.surname = surname;
		this.categories = category;
	}
		
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public Set<BusCategory> getCategories() {
		return categories;
	}
	
	public void setCategories(Set<BusCategory> categories) {
		this.categories = categories;
	}
	
	public long getId() {
		return idd;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}


}
