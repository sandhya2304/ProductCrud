package com.sandy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product
{
	@Id @GeneratedValue
	private Long id;
	@NotNull
    @Size(min=3,max=15)
	private String designation;
	@DecimalMin("100")
	private double price;
	private int quantity;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public Product(String designation, double price, int quantity) {
		super();
		this.designation = designation;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	
	

}
