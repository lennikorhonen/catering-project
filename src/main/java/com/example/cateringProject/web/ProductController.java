package com.example.cateringProject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.cateringProject.domain.CategoryRepository;
import com.example.cateringProject.domain.Product;
import com.example.cateringProject.domain.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public String getProducts(Model model) {
		model.addAttribute("products", repository.findAll());
		return "productlist";
	}
	
	@RequestMapping(value = "/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("category", crepository.findAll());
		return "addproduct";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editProduct(@PathVariable("id") Long productId, Model model) {
		model.addAttribute("product", repository.findById(productId));
		model.addAttribute("category", crepository.findAll());
		return "editproduct";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Product product) {
		repository.save(product);
		return "redirect:productlist";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") Long productId, Model model) {
		repository.deleteById(productId);
		return "redirect:../productlist";
	}
}
