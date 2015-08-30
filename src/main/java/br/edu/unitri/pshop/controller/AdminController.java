package br.edu.unitri.pshop.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.pshop.data.CategoryRepository;
import br.edu.unitri.pshop.data.OrderRepository;
import br.edu.unitri.pshop.model.Category;
import br.edu.unitri.pshop.model.Order;

@Named
@SessionScoped
public class AdminController extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoryRepository categoryRepository;
	
	@Inject
	private OrderRepository orderRepository;


	private Order selectOrder;

	@PostConstruct
	public void init() {
	}

	public List<Category> getCategorys() {
		return categoryRepository.findAll();
	}
	
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	public Order getSelectOrder() {
		return selectOrder;
	}

	public void setSelectOrder(Order selectOrder) {
		this.selectOrder = selectOrder;
	}

	
	public void handleClose() {
		selectOrder = new Order();
	}
}
