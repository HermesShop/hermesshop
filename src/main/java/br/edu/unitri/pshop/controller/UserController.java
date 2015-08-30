package br.edu.unitri.pshop.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.edu.unitri.pshop.data.ProfileRepository;
import br.edu.unitri.pshop.data.UserRepository;
import br.edu.unitri.pshop.model.Profile;
import br.edu.unitri.pshop.model.User;
import br.edu.unitri.pshop.service.UserRegistration;
import br.edu.unitri.pshop.util.JsfUtils;


@Named
@SessionScoped
public class UserController extends AbstractController implements Serializable {
	private static final long serialVersionUID = 6920835789315490285L;

	@Inject
	private UserRegistration userServices;
	
	@Inject
	private UserRepository userRepository;

	@Inject
	private ProfileRepository profileRepository;

	private User user = new User();
	private Long idUser;
	List<User> users;

	// bundle com as mensagens internacionalizaveis
	private ResourceBundle bundle;

	/** The itens state. */
	List<SelectItem> itensProfile = null;

	private List<Profile> listProfile = null;

	/** The itens state. */
	List<SelectItem> itensMemberType = null;

	@PostConstruct
	public void init() {
		this.bundle = ResourceBundle.getBundle("br.com.pshop.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		itensProfile = new ArrayList<SelectItem>();
		listProfile = new ArrayList<Profile>();
	}

	public List<User> getUsers() {
		users = userRepository.findAll();
		return users;
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idUser != null) {
				user = userRepository.findById(idUser);
				if (user == null) {
					redirect("list.jsf");
				}
			} else {
				user = new User();
			}
		}
	}

	public List<SelectItem> getProfiles() {

		setListProfile(profileRepository.findAll());
		for (Profile p : listProfile) {
			itensProfile.add(new SelectItem(p, p.getDescription()));
		}
		return itensProfile;
	}

	public String save() {
		try {
			userServices.register(user);
			addMessageFromBundleInFlash(FacesMessage.SEVERITY_INFO,
					"label.user.save");
			limpar();
			return "list?faces-redirect=true";
		} catch (Exception e) {
			getFacesContext().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", bundle
							.getString("label.user-exists")));
		}
		return null;
	}

	public void mSave() throws Exception {
		userServices.register(user);
		JsfUtils.showInfoMessage("Cliente Salvo!");
	}

	public String remove() {
		userServices.delete(user);
		addMessageFromBundleInFlash(FacesMessage.SEVERITY_INFO,
				"label.user.exclude");
		limpar();
		return "list?faces-redirect=true";
	}

	public void buscar() {
		limpar();
	}

	public String selecionaUser(String email) {
		user = userRepository.findSpecificUserByEmail(email);
		if (user != null) {
			return "pm:usercheck";
		} else {
			JsfUtils.showErrorMessage("User nao encontrado!");
			return null;
		}
	}

	public String listarUsers() {
		users = userRepository.findAll();
		return "pm:listaUsers";
	}

	public String novoUser() {
		user = new User();
		return "pm:new";
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	/**
	 * Faz o redirecionamento para uma pagina (caminho relativo)
	 * 
	 * @param pagina
	 */
	private void redirect(String pagina) {
		try {
			getFacesContext().getExternalContext().redirect(pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recebe como parametro uma chave, faz a traducao da chave para a mensagem
	 * olhando o bundle e a adiciona no escopo {@link Flash} da aplicacao.
	 * 
	 * @param chave
	 * @param severidade
	 */
	public void addMessageFromBundleInFlash(Severity severidade, String chave) {
		Flash flash = getFacesContext().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		getFacesContext().addMessage(null,
				new FacesMessage(severidade, null, bundle.getString(chave)));
	}

	public User getLoggedUsers() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String userEmail = context.getUserPrincipal().getName();

			user = userRepository.findSpecificUserByEmail(userEmail);
		}

		return user;
	}

	public User getLoggedUser() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();

		user = userRepository.findSpecificUserByUsername(context.getUserPrincipal()
				.getName());

		return user;
	}

	public boolean isUserAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}

	public boolean isUserRoot() {
		return getRequest().isUserInRole("ROOT");
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

	}

	private void limpar() {
		idUser = null;
		user = new User();
	}

	public List<SelectItem> getItensProfile() {
		return itensProfile;
	}

	public void setItensProfile(List<SelectItem> itensProfile) {
		this.itensProfile = itensProfile;
	}

	public List<Profile> getListProfile() {
		return listProfile;
	}

	public void setListProfile(List<Profile> listProfile) {
		this.listProfile = listProfile;
	}

}