package com.sandy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandy.dao.ProductRepository;
import com.sandy.model.Product;

@Controller
public class ProductController
{
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value="/index")
	public String index(Model model,
			@RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="size",defaultValue="5") int s,
			@RequestParam(name="search",defaultValue="")String search )
	{
		Page<Product> products=productRepository.search("%"+search+"%", new PageRequest(p, s));
		
		model.addAttribute("listProduct",products.getContent());
		int pages[] = new int[products.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",s);
		model.addAttribute("pageCurrent",p);
		model.addAttribute("search",search);
		
		return "products";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(Long id,String search,int page,int size)
	{
		productRepository.delete(id);
		
		return "redirect:/index?page="+page+"&size="+size+"&search="+search;
	}
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formProduct(Model model)
	{
		model.addAttribute("product",new Product());
		return "formProduct";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String editProduct(Model model,Long id)
	{
		Product p=productRepository.findOne(id);
		model.addAttribute("product",p);
		return "editProduct";
	}
	

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveProduct(Model model,@Valid Product product,BindingResult results)
	{
		if(results.hasErrors())
		{
			return "formProduct";
		}
		
		productRepository.save(product);
		return "confirmation";
	}
	
	@RequestMapping(value="/")
	public String home()
	{
		return "redirect:/index";
	}
	
	@RequestMapping(value="/403")
	public String error()
	{
		return "403";
	}
	
	@RequestMapping(value="/login")
	public String login()
	{
		return "login";
	}
	

}
