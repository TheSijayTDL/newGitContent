package lv.venta.services;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IFilterProduct {
	
	// Filtration
	
	// Price less than X
	ArrayList<Product> filterByPriceLessThan(float priceThreshhold) throws Exception;
	
	// Quantity less than X
	ArrayList<Product> filterByQuantityLessThan(int quantityThreshhold) throws Exception;
	
	// Sorting
	ArrayList<Product> sort();
	
	
}
