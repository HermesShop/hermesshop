package br.edu.unitri.pshop.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.pshop.data.CategoryRepository;
import br.edu.unitri.pshop.model.Category;
import br.edu.unitri.pshop.service.CategoryRegistration;

@Named
@SessionScoped
public class CategoryController extends AbstractController implements
		Serializable {

	private static final long serialVersionUID = -1199344444630381194L;

	@Inject
	private CategoryRepository categoryRepository;

	@Inject
	private CategoryRegistration categoryRegistration;

	private List<Category> categorys;

	private Category category;

	private Long idCategory;

	@PostConstruct
	public void init() {
		categorys = new ArrayList<Category>();

		this.bundle = ResourceBundle.getBundle("br.com.pshop.bundle.messages",
				getFacesContext().getViewRoot().getLocale());
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 * 
	 * @throws IOException
	 */
	public void load() throws IOException {
		if (!getFacesContext().isPostback()) {
			if (idCategory != null) {
				category = categoryRepository.findById(idCategory);
				if (category == null) {
					setRedirectPage("index.jsf");
				}
			} else {
				category = new Category();
			}
		}
	}

	public String save() {
		try {
			if (category.getId() != null) {
				categoryRegistration.update(category);
			} else {
				categoryRegistration.register(category);
			}
			
			limpar();
			return "category?faces-redirect=true";
		} catch (Exception e) {
			System.out.println("-->" + e.getMessage());
			/*
			 * getFacesContext().addMessage( null, new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, "", bundle
			 * .getString("label.animal-exists")));
			 */
		}
		return null;
	}

	public String remove() {
		categoryRegistration.remove(category);
		/*addMessageFromBundleInFlash(FacesMessage.SEVERITY_INFO,
				"label.city.exclude"); */
		limpar();
		return "category?faces-redirect=true";
	}
	
	public String cancelar() {
		limpar();
		return "category?faces-redirect=true";
	}

	public List<Category> getCategorys() {
		categorys = categoryRepository.findAll();
		return categorys;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	private void limpar() {
		idCategory = null;
		category = new Category();
	}
}
