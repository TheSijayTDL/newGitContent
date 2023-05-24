package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repos.IProductRepo;
import lv.venta.services.ICRUDProductService;

@Service
public class CRUDProductServiceImpl implements ICRUDProductService {

	@Autowired
	private IProductRepo productRepo;
	
	@Override
	public void addNewProduct(String title, String description, float price, int quantity) throws Exception {
		if (productRepo.existsByTitleAndDescriptionAndPrice(title, description, price)) {
			Product temp = productRepo.findByTitleAndDescriptionAndPrice(title, description, price);
			temp.setQuantity(temp.getQuantity() + quantity);
			productRepo.save(temp);
		} else {
			Product newProduct = new Product(title, description, price, quantity);
			productRepo.save(newProduct);
		}
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public Product retrieveProductByID(long id) throws Exception {
		if (id > 0) {
			if (productRepo.existsById(id)) {
				Product temp = productRepo.findById(id).get();
				return temp;
			} else {
				throw new Exception("Product with the specified ID not found!");
			}
		} else {
			throw new Exception("Incorrect id!");
		}
	}

	@Override
	public void updateProductByID(long id, String title, String description, float price, int quantity) throws Exception {
		if (id > 0) {
			if (productRepo.existsById(id)) {
				Product temp = productRepo.findById(id).get();
				temp.setTitle(title);
				temp.setDescription(description);
				temp.setPrice(price);
				temp.setQuantity(quantity);
				productRepo.save(temp);
			} else {
				throw new Exception("There is no product with the specified ID!");
			}
		} else {
			throw new Exception("Incorrect id!");
		}
	}

	@Override
	public void deleteProductByID(long id) throws Exception {
		if (productRepo.existsById(id)) {
			productRepo.deleteById(id);
		} else {
			throw new Exception("Product with the specified ID not found!!");
		}
		
		
	}
	
}
