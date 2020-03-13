package com.spring.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public Category findByid(int id);
}
