package lv.autoosta.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name = "city_table") // DB table
@Entity
public class City {
	@Column(name = "Title")
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+")
	@Size(min = 3, max = 35)
	private String title;
	
	@Column(name = "Addressofstation")
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+[0-9]*")
	@Size(min = 5, max = 35)
	private String addressOfStation;
	
	@Column(name = "Idc")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idc;
	
	@ManyToMany(mappedBy = "cities")
	private Collection<Trip> trips;
	
	public City() {
		this.title = "Test";
		this.addressOfStation = "Test street 1";
	}
	
	public City(@NotNull @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+") @Size(min = 3, max = 35) String title,
			@NotNull @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+[0-9]*") @Size(min = 5, max = 35) String addressOfStation) {
		this.title = title;
		this.addressOfStation = addressOfStation;
	}

	public void addTrip(Trip inputTrip) {
		if (!trips.contains(inputTrip)) {
			trips.add(inputTrip);
		}
	}
	
	public void removeTrip(Trip inputTrip) {
		if (trips.contains(inputTrip)) {
			trips.remove(inputTrip);
		}
	}
}
