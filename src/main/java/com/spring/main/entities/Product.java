package com.spring.main.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String imagePath;
	private double price;
	private int quantity;
	@ManyToOne
	private Category category;
	@ManyToMany(mappedBy = "products")
	@Cascade({CascadeType.DELETE})
	private List<Cart> carts;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String title, String imagePath, double price, int quantity, Category category,
			List<Cart> carts) {
		super();
		this.id = id;
		this.title = title;
		this.imagePath = imagePath;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.carts = carts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", imagePath=" + imagePath + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

}
