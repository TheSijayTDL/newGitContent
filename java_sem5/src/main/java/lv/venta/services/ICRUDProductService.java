package lv.venta.services;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface ICRUDProductService {
	// CRUD
	
	// C - Create
	public abstract void addNewProduct(String title, String description, float price, int quantity) throws Exception;
	
	// R - Retrieve All
	public abstract ArrayList<Product> retrieveAllProducts();
	
	// R - Retrieve by ID
	public abstract Product retrieveProductByID(long id) throws Exception;
	
	// U - Update
	public abstract void updateProductByID(long id, String title, String description, float price, int quantity) throws Exception;
	
	// D - Delete
	public abstract void deleteProductByID(long id) throws Exception;
	
}
