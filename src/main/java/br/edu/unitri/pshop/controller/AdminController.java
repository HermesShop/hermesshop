package br.edu.unitri.pshop.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.pshop.data.ItemOrderRepository;
import br.edu.unitri.pshop.data.OrderRepository;
import br.edu.unitri.pshop.data.ProductRepository;
import br.edu.unitri.pshop.model.Order;

@Named
@SessionScoped
public class AdminController extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProductRepository productRepository;

	@Inject
	private OrderRepository orderRepository;

	@Inject
	private ItemOrderRepository itemOrderRepository;

	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

}
