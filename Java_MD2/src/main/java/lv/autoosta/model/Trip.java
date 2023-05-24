package lv.autoosta.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Table(name = "trip_table") // DB table
@Entity
public class Trip {
	@Column(name = "Startdatetime")
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDateTime startDateTime;
	
	@Column(name = "Duration")
	@Min(1)
	@Max(100)
	private float duration;
	
	@Column(name = "Idtr")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idtr;
	
	@ManyToMany
	@JoinTable(name = "trip_city_table", joinColumns = @JoinColumn(name = "Idc"), inverseJoinColumns = @JoinColumn(name = "Idtr"))
	private Collection<City> cities = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "Idd")
	private Driver driver;
	
	public Trip() {
		this.startDateTime = LocalDateTime.now();
		this.duration = 0;
	}
	
	public Trip(@NotNull LocalDateTime startDateTime, @Min(1) @Max(100) float duration, Collection<City> cities, Driver driver) {
		this.startDateTime = startDateTime;
		this.duration = duration;
		this.cities = cities;
		this.driver = driver;
	}

	public void addCity(City inputCity) {
		if (!cities.contains(inputCity)) {
			cities.add(inputCity);
		}
	}
	
	public void removeCity(City inputCity) {
		if (cities.contains(inputCity)) {
			cities.remove(inputCity);
		}
	}
	
	private static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			
	public String getStartDateTime() {
		return startDateTime.format(pattern);
	}
	
	public LocalDateTime getTimeInfo() {
		return startDateTime;
	}
	
	public float getDuration() {
		return duration;
	}
	
	public long getIdtr() {
		return idtr;
	}
	
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
}
