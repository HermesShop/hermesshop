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

import br.edu.unitri.pshop.data.CategoryRepository;
import br.edu.unitri.pshop.data.ClientRepository;
import br.edu.unitri.pshop.model.Category;
import br.edu.unitri.pshop.model.Client;
import br.edu.unitri.pshop.service.ClientRegistration;

@Named
@SessionScoped
public class ClientController extends AbstractController implements
		Serializable {

	private static final long serialVersionUID = -1199344444630381194L;

	@Inject
	private ClientRepository clientRepository;
	
	@Inject
	private CategoryRepository categoryRepository;

	@Inject
	private ClientRegistration clientRegistration;

	private List<Client> clients;

	private Client client;

	private Long idClient;
	
	/** The itens emporium. */
	List<SelectItem> itensCategory = null;

	@PostConstruct
	public void init() {
		clients = new ArrayList<Client>();
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
			if (idClient != null) {
				client = clientRepository.findById(idClient);
				if (client == null) {
					setRedirectPage("index.jsf");
				}
			} else {
				client = new Client();
			}
		}
	}

	public String save() {
		try {
			if (client.getId() != null) {
				clientRegistration.update(client);
			} else {
				clientRegistration.register(client);
			}
			
			limpar();
			return "client?faces-redirect=true";
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
		clientRegistration.remove(client);
		/*addMessageFromBundleInFlash(FacesMessage.SEVERITY_INFO,
				"label.city.exclude"); */
		limpar();
		return "client?faces-redirect=true";
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
		return "client?faces-redirect=true";
	}

	public List<Client> getClients() {
		clients = clientRepository.findAll();
		return clients;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	private void limpar() {
		idClient = null;
		client = new Client();
	}
}
