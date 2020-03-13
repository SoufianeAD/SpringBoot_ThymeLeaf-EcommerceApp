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

import com.spring.main.entities.Client;
import com.spring.main.entities.Product;
import com.spring.main.repository.CategoryRepository;
import com.spring.main.repository.ClientRepository;
import com.spring.main.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/products/{id}")
	public String products(@PathVariable("id") int id, Model model) {
		System.out.println(id);
		model.addAttribute("products", productRepository.findByCategoryId(id));
		return "Products";
	}
	
	@GetMapping("/productDetails/{id}")
	public String productDetails(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		System.out.println(id);
		model.addAttribute("product",  productRepository.findById(id));
		model.addAttribute("isAuth", (String)request.getSession().getAttribute("Auth"));
		System.out.println((String)request.getSession().getAttribute("Auth"));
		return "ProductDetails";
	}
	
	@GetMapping("/newProductForm")
	public String newProductForm(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("product", new Product());
		return "NewProduct";
	}
	
	@PostMapping("/newProduct")
	public String newProduct(@ModelAttribute("product") Product product,
			@RequestParam("image") String imagePath,
			@RequestParam("category") String idCategory) {
		
		System.out.println(idCategory);
		product.setCategory(categoryRepository.findByid(Integer.parseInt(idCategory)));
		product.setImagePath(imagePath);
		productRepository.save(product);
		return "redirect:/category/categories";
	}
	
	@GetMapping("/addProductToCart/{id}")
	public String shopProduct(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		Product product = productRepository.findById(id);
		String[] t = ((String)request.getSession().getAttribute("Auth")).split("/");
		Client client = clientRepository.findByUserNameAndPassword(t[0], t[1]);
		client.getCart().getProducts().add(product);
		model.addAttribute("client", client);
		model.addAttribute("products", client.getCart().getProducts());
		return "Cart";
	}
	
	@GetMapping("/productsList")
	public String productsList(Model model) {
		model.addAttribute("products", productRepository.findAll());
		System.out.println(productRepository.findAll());
		return "ProductsList";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		//productRepository.deleteCartProducts(id);
		productRepository.deleteById(id);
		return "redirect:/product/productsList";
	}
	
}
