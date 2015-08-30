package br.edu.unitri.pshop.service;

import java.sql.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.unitri.pshop.data.ClientRepository;
import br.edu.unitri.pshop.exception.ClientNotFoundException;
import br.edu.unitri.pshop.model.Client;
import br.edu.unitri.pshop.model.ItemOrder;
import br.edu.unitri.pshop.model.Order;

@Stateless
public class OrderRegistration {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Order> orderEventSrc;

	@Inject
	private ClientRepository clientRepository;

	public void register(Order order) throws Exception {
		log.info("Registering " + order.getId());
		em.persist(order);
		orderEventSrc.fire(order);
	}

	public Order addOrder(Order shopcarOrder, String email, String password)
			throws ClientNotFoundException {
		Client cliente = clientRepository.findByEmailAndPassword(email,
				password);
		if (cliente == null) {
			throw new ClientNotFoundException("Cliente não encontrado");
		}

		for (ItemOrder item : shopcarOrder.getItens()) {
			System.out.println("_" + item.toString());
		}
		shopcarOrder.setDate(new Date(0));
		shopcarOrder.setClient(cliente);

		if (shopcarOrder.getId() != null) {
			em.merge(shopcarOrder);
		} else {
			em.persist(shopcarOrder);

			for (ItemOrder item : shopcarOrder.getItens()) {
				Order order = new Order();
				order.setId(shopcarOrder.getId());
				item.setOrder(order);
			}
		}

		return shopcarOrder;
	}

	public void update(Order order) {
		log.info("Registering " + order.getId());
		em.merge(order);
		orderEventSrc.fire(order);
	}
}
