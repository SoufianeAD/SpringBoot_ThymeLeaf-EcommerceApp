package com.spring.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByCategoryId(int id);
	public Product findById(int id);
	@Query(value = "delete from cart_products WHERE products_id=?1", nativeQuery = true)
	public void deleteCartProducts(int idProduit); 
}
