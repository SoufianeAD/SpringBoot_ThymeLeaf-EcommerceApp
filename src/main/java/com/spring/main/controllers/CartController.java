package com.spring.main.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.main.entities.Client;
import com.spring.main.entities.Product;
import com.spring.main.repository.CartRepository;
import com.spring.main.repository.ClientRepository;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/cartProducts")
	public String cartProducts(Model model, HttpServletRequest request) {
		String[] t = ((String)request.getSession().getAttribute("Auth")).split("/");
		Client client = clientRepository.findByUserNameAndPassword(t[0], t[1]);
		model.addAttribute("client", client);
		model.addAttribute("products", client.getCart().getProducts());
		return "Cart";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(HttpServletRequest request, @PathVariable("id") int id) {
		String[] t = ((String)request.getSession().getAttribute("Auth")).split("/");
		Client client = clientRepository.findByUserNameAndPassword(t[0], t[1]);
		List<Product> products = client.getCart().getProducts();
		for (Product product : products) {
			if(product.getId() == id) {
				products.remove(product);
				break;
			}
		}
		return "redirect:/cart/cartProducts";
	}
	
}
