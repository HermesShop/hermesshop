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

	public Order addOrder(Order shopcarOrder, String email, String password) throws ClientNotFoundException {
		Client cliente = clientRepository.findByEmailAndPassword(email, password);
		if (cliente == null) {
			throw new ClientNotFoundException("Cliente não encontrado");
		}
		shopcarOrder.setDate(new Date(0));
		//shopcarOrder.setClient(cliente);
		em.persist(shopcarOrder);
		return shopcarOrder;
	}
}
