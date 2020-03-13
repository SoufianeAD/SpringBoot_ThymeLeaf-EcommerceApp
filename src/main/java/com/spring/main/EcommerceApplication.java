package com.spring.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.main.entities.Admin;
import com.spring.main.entities.Client;
import com.spring.main.repository.AdminRepository;
import com.spring.main.repository.ClientRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
	private AdminRepository adminRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Admin> admins = adminRepository.findAll();
		if(admins.size() == 0) {
			Admin admin = new Admin();
			admin.setUserName("admin");
			admin.setPassword("admin1234");
			adminRepository.save(admin);
		}
	}

}
