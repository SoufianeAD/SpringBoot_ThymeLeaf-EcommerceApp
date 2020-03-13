package com.spring.main.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.main.entities.Cart;
import com.spring.main.entities.Client;
import com.spring.main.repository.CartRepository;
import com.spring.main.repository.ClientRepository;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CartRepository cartRepository;
	
	@GetMapping("/newClientForm")
	public String newClientForm(Model model) {
		model.addAttribute("client", new Client());
		return "NewClientForm";
	}
	
	@GetMapping("/clients")
	public String clients(Model model) {
		model.addAttribute("clients", clientRepository.findAll());
		return "Clients";
	}
	
	@PostMapping("/newClient")
	public String newClient(@ModelAttribute("client") Client client) {
		client.setCart(cartRepository.save(new Cart()));
		clientRepository.save(client);
		return "Categories";
	}
	
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		return "Login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {
		request.getSession().removeAttribute("Auth");
		return "redirect:/category/categories";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request) {
		Client client = clientRepository.findByUserNameAndPassword(userName, password);
		if(client == null) {
			return "ErrorLogin";
		}
		request.getSession().removeAttribute("admin");
		request.getSession().setAttribute("Auth", userName + "/" + password);
		return "redirect:/category/categories";
	}
}
