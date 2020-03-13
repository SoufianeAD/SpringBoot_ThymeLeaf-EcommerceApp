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

import com.spring.main.entities.Admin;
import com.spring.main.repository.AdminRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	
	@GetMapping("/loginAdminForm")
	public String loginAdminForm() {
		return "LoginAdmin";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request) {
		Admin admin = adminRepository.findByUserNameAndPassword(userName, password);
		if(admin == null) {
			return "ErrorLogin";
		}
		request.getSession().removeAttribute("Auth");
		request.getSession().setAttribute("admin", true);
		return "redirect:/category/categoriesList";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("admin");
		return "redirect:/category/categories";
	}
	
	@GetMapping("/newAdminForm")
	public String newAdminForm(Model model) {
		model.addAttribute("newAdmin", new Admin());
		return "NewAdmin";
	}
	
	@GetMapping("/adminList")
	public String adminList(Model model) {
		List<Admin> admins = adminRepository.findAll();
		model.addAttribute("admins", admins);
		return "AdminList";
	}
	
	@GetMapping("/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable("id") int id) {
		adminRepository.deleteById(id);
		return "redirect:/admin/adminList";
	}
	
	@PostMapping("/newAdmin")
	public String newAdmin(@ModelAttribute("newAdmin") Admin admin) {
		adminRepository.save(admin);
		return "redirect:/admin/adminList";
	}
}
