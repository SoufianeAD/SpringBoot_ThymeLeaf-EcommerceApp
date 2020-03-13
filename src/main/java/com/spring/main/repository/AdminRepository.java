package com.spring.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findByUserNameAndPassword(String userName, String password);
}
