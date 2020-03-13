package com.spring.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	public Client findByUserNameAndPassword(String userName, String password);
	public Client findById(int id);
}
