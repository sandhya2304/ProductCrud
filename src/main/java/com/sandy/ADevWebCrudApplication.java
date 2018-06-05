package com.sandy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sandy.dao.ProductRepository;
import com.sandy.model.Product;

@SpringBootApplication
public class ADevWebCrudApplication {

	public static void main(String[] args) {
		ApplicationContext ctxt=SpringApplication.run(ADevWebCrudApplication.class, args);
		
		ProductRepository repo=ctxt.getBean(ProductRepository.class);
		
		/*repo.save(new Product("HCL",30000,1));
		repo.save(new Product("Tech",19000,5));
		repo.save(new Product("Wipro",15000,2));*/
		
		repo.findAll().forEach(p -> System.out.println(p.getDesignation()+" "+p.getPrice()+" "+p.getQuantity()));
		
		
	}
}
