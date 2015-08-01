package br.edu.unitri.pshop.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.pshop.data.ProductRepository;
import br.edu.unitri.pshop.model.Product;

@Named
@RequestScoped
public class ProductController {

	@Inject
	private ProductRepository productRepository;
	
	private List<Product> products;

	@PostConstruct
	public void init() {
		products = productRepository.findAll();
	}

	public List<Product> getProducts() {
		return products;
	}
}
