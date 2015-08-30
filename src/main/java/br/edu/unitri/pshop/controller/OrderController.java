package br.edu.unitri.pshop.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.pshop.data.ItemOrderRepository;
import br.edu.unitri.pshop.data.OrderRepository;
import br.edu.unitri.pshop.model.ItemOrder;
import br.edu.unitri.pshop.model.Order;
import br.edu.unitri.pshop.service.OrderRegistration;

@Named
@SessionScoped
public class OrderController extends AbstractController implements Serializable {

	private static final long serialVersionUID = -1199344444630381194L;

	@Inject
	private OrderRepository orderRepository;

	@Inject
	private OrderRegistration orderRegistration;

	@Inject
	private ItemOrderRepository itemOrderRepository;

	private List<Order> orders;

	private Order order;

	private Long idOrder;

	private String id;

	@PostConstruct
	public void init() {
		orders = new ArrayList<Order>();

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
			if (id != null) {
				order = orderRepository.findById(Long.valueOf(id));
				if (order == null) {
					setRedirectPage("index.jsf");
				}
			} else {
				order = new Order();
			}
		}
	}

	public String save() {
		try {
			if (order.getId() != null) {
				orderRegistration.update(order);
			} else {
				orderRegistration.register(order);
			}
			
			addMessageFromBundleInFlash(FacesMessage.SEVERITY_INFO,
					"label.animal.save");
			limpar();
			return "order?faces-redirect=true";
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

	public String cancelar() {
		limpar();
		return "order?faces-redirect=true";
	}

	public List<Order> getOrders() {
		orders = orderRepository.findAll();
		return orders;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	private void limpar() {
		idOrder = null;
		order = new Order();
	}

	public List<ItemOrder> selectItemOrder(Long id) {
		return itemOrderRepository.findByOrderId(id);
	}

}
