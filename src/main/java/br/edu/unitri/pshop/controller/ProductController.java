package br.edu.unitri.pshop.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.edu.unitri.pshop.data.CategoryRepository;
import br.edu.unitri.pshop.data.ProductRepository;
import br.edu.unitri.pshop.model.Category;
import br.edu.unitri.pshop.model.Product;
import br.edu.unitri.pshop.service.ProductRegistration;

@Named
@SessionScoped
public class ProductController extends AbstractController implements
		Serializable {

	private static final long serialVersionUID = -1199344444630381194L;

	@Inject
	private ProductRepository productRepository;

	@Inject
	private CategoryRepository categoryRepository;

	@Inject
	private ProductRegistration productRegistration;

	private List<Product> products;

	private Product product;

	private Long idProduct;

	private StreamedContent imagem;

	/** The itens emporium. */
	List<SelectItem> itensCategory = null;

	@PostConstruct
	public void init() {
		products = new ArrayList<Product>();
		itensCategory = new ArrayList<SelectItem>();

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
			if (idProduct != null) {
				product = productRepository.findById(idProduct);
				if (product == null) {
					setRedirectPage("index.jsf");
				}
			} else {
				product = new Product();
			}
		}
	}

	public String save() {
		try {
			if (product.getId() != null) {
				productRegistration.update(product);
			} else {
				productRegistration.register(product);
			}
			limpar();
			return "product?faces-redirect=true";
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
		productRegistration.remove(product);
		/*
		 * addMessageFromBundleInFlash(FacesMessage.SEVERITY_INFO,
		 * "label.city.exclude");
		 */
		limpar();
		return "product?faces-redirect=true";
	}

	public List<SelectItem> getCategorys() {

		List<Category> categorys = categoryRepository.findAll();
		for (Category p : categorys) {
			itensCategory.add(new SelectItem(p, p.getName()));
		}
		return itensCategory;
	}

	public String cancelar() {
		limpar();
		return "product?faces-redirect=true";
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

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	private void limpar() {
		idProduct = null;
		product = new Product();
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			setImagem(new DefaultStreamedContent(event.getFile()
					.getInputstream()));
			byte[] image = event.getFile().getContents();
			this.product.setImagem(image);
		} catch (IOException ex) {
			
		}
	}

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

}
