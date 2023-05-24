package lv.venta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.services.ICRUDProductService;

@Controller
public class FirstController {
	
	@Autowired
	private ICRUDProductService CRUDservice;
	
	@GetMapping("/hello") // localhost:8080/hello
	public String getHelloFunc() {
		System.out.println("test");
		return "hello-page"; // html page
	}
	
	@GetMapping("/msg") // localhost:8080/msg
	public String getMsgFunc(Model model) {
		model.addAttribute("message", "Hello World!");
		return "msg-page"; // html page
	}
	
	@GetMapping("/one-product") // localhost:8080/one-product
	public String getOneProductFunc(Model model) {
		Product prod = new Product("Apple", "Tasty", 1.2f, 9);
		model.addAttribute("message", prod);
		return "one-product-page"; // html page
	}
	
	@GetMapping("/product-list") // localhost:8080/product-list
	public String getProductListFunc(Model model) {
		model.addAttribute("message", CRUDservice.retrieveAllProducts());
		return "product-list-page"; // html page
	}
	
	@GetMapping("/product-list-find") // localhost:8080/product-list-find?id=2
	public String getProductListFindFunc(@RequestParam("id") long id, Model model) {
		try {
			Product prod = CRUDservice.retrieveProductByID(id);
			model.addAttribute("message", prod);
			return "one-product-page";	
		} catch (Exception e) {
			model.addAttribute("messageError", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/product-list-find/{id}") // localhost:8080/product-list-find/2
	public String getProductListFindByIDFunc(@PathVariable("id") long id, Model model) {
		try {
			Product prod = CRUDservice.retrieveProductByID(id);
			model.addAttribute("message", prod);
			return "one-product-page";	
		} catch (Exception e) {
			model.addAttribute("messageError", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/add-product") // localhost:8080/add-product
	public String getAddProductFunc(Model model) {
		model.addAttribute("product", new Product());
		return "add-product-page";
	}
	
	@PostMapping("/add-product") // localhost:8080/add-product
	public String postAddProductFunc(@Valid Product product, BindingResult result) {
		
		if (!result.hasErrors()) {
			try {
				CRUDservice.addNewProduct(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
				return "redirect:/product-list";
			} catch (Exception e) {
				return "redirect:/error";
			}
		} else {
			return "add-product-page";
		}
	}
	
	@GetMapping("/update-product/{id}") // localhost:8080/update-product/2
	public String getUpdateProductFunc(@PathVariable("id") long id, Model model) {
		try {
			Product prod = CRUDservice.retrieveProductByID(id);
			model.addAttribute("product", prod);
			return "update-product-page";
		} catch (Exception e) {
			model.addAttribute("messageError", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/update-product/{id}") // localhost:8080/update-product/2
	public String postUpdateProductFunc(@PathVariable("id") long id, @Valid Product product, BindingResult result) {
		
		if (!result.hasErrors()) {
			try {
				CRUDservice.updateProductByID(id, product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
				return "redirect:/product-list-find/" + id;
			} catch (Exception e) {
				return "redirect:/error";
			}
		} else {
			return "update-product-page";
		}
	}
	
	@GetMapping("/error")
	public String getErrorFunc(Model model) {
		model.addAttribute("messageError", "Wrong ID");
		return "error-page";
	}
	
	@GetMapping("/delete-product/{id}") // localhost:8080/delete-product/2
	public String getDeleteProductFunc(@PathVariable("id") long id, Model model) {
		try {
		CRUDservice.deleteProductByID(id);
		model.addAttribute("message", CRUDservice.retrieveAllProducts());
		return "product-list";

		} catch (Exception e) {
			model.addAttribute("messageError", e.getMessage());
			return "error-page";
		}
	}
	
}
