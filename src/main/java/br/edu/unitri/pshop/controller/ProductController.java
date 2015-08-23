package br.edu.unitri.pshop.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.pshop.data.ProductRepository;
import br.edu.unitri.pshop.model.Product;

@Named
@SessionScoped
public class ProductController extends AbstractController implements Serializable {

	private static final long serialVersionUID = -1199344444630381194L;

	@Inject
	private ProductRepository productRepository;

	private List<Product> products;

	private Product product;

	private String id;

	@PostConstruct
	public void init() {
		products = new ArrayList<Product>();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 * 
	 * @throws IOException
	 */
	public void load() throws IOException {
		if (!getFacesContext().isPostback()) {
			if (id != null) {
				product = productRepository.findById(Long.valueOf(id));
				if (product == null) {
					setRedirectPage("index.jsf");
				}
			} else {
				product = new Product();
			}
		}
	}


	public List<Product> getProducts() {
		products = productRepository.findAll();
		return products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
