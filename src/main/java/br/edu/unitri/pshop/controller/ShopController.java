package br.edu.unitri.pshop.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RateEvent;

import br.edu.unitri.pshop.data.CategoryRepository;
import br.edu.unitri.pshop.data.ProductRepository;
import br.edu.unitri.pshop.model.Category;
import br.edu.unitri.pshop.model.Product;

@Named
@SessionScoped
public class ShopController extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoryRepository categoryRepository;

	@Inject
	private ProductRepository productRepository;

	private Integer rating;

	private Product selectProduct;

	private Long idProduct;

	private List<Product> products;

	@PostConstruct
	public void init() {
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 * 
	 * @throws IOException
	 */
	public void loadProducts() throws IOException {
		if (!getFacesContext().isPostback()) {
			if (idProduct != null) {
				products = productRepository.findByCategoryId(idProduct);
				if (products == null) {
					setRedirectPage("index.jsf");
				}
			} else {
				products = productRepository.findAll();
			}
			
			idProduct = null;
		}
	}

	public void onrate(RateEvent rateEvent) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Rate Event", "You rated:"
						+ ((Integer) rateEvent.getRating()).intValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void oncancel() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Cancel Event", "Rate Reset");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<Category> getCategorys() {
		return categoryRepository.findAll();
	}

	public Product getSelectProduct() {
		return selectProduct;
	}

	public void setSelectProduct(Product selectProduct) {
		this.selectProduct = selectProduct;
	}

	public void handleClose() {
		selectProduct = new Product();
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
