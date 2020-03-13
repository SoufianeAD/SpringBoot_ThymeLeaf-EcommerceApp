package com.spring.main.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.main.entities.Category;
import com.spring.main.entities.Product;
import com.spring.main.repository.CategoryRepository;
import com.spring.main.repository.ProductRepository;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/categories")
	public String categories(Model model, HttpServletRequest request ) {
		model.addAttribute("categories", categoryRepository.findAll());
		String session = (String)request.getSession().getAttribute("Auth");
		System.out.println("Session:: " + session);
		return "categories";
	}
	
	@GetMapping("/newCategoryForm")
	public String newCategoryForm(Model model) {
		model.addAttribute("category", new Category());
		return "NewCategoryForm";
	}
	
	@PostMapping("/newCategory")
	public String newCategory(@ModelAttribute("category") Category category, @RequestParam("image") String imagePath) {
		category.setImagePath(imagePath);
		categoryRepository.save(category);
		return "redirect:/category/categories";
	}
	
	@GetMapping("/categoriesList")
	public String categoriesList(Model model, HttpServletRequest request) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "CategoriesList";
	}
	
	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable("id") int id) {
		List<Product> products = productRepository.findByCategoryId(id);
		for (Product product : products) {
			productRepository.deleteById(product.getId());
		}
		categoryRepository.deleteById(id);
		return "redirect:/category/categoriesList";
	}

}
