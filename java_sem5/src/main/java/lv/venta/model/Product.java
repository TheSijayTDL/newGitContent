package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name = "product_table") // DB table
@Entity
public class Product {
	@Column(name = "Title") // Mysql will be underscore e.g. title
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+")
	@Size(min = 4, max = 100)
	private String title;
	
	@Column(name = "Description") // Mysql will be underscore e.g. title
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z0-9\\ ]+")
	@Size(min = 4, max = 400)
	private String description;
	
	@Column(name = "Price") // Mysql will be underscore e.g. title
	@Min(0)
	@Max(10000)
	private float price;
	
	@Column(name = "Quantity") // Mysql will be underscore e.g. title
	@Min(0)
	@Max(10000)
	private int quantity;
	
	@Column(name = "Id") // Mysql will be underscore e.g. title
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public Product() {
		this.title = "Test";
		this.description = "Test";
		this.price = 0;
		this.quantity = 0;
	}
	
	public Product(String title, String description, float price, int quantity) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public long getId() {
		return id;
	}
	
	
}
