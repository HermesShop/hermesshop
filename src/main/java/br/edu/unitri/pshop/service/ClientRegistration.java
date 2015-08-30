package br.edu.unitri.pshop.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.unitri.pshop.model.Client;

@Stateless
public class ClientRegistration {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Client> clientEventSrc;

	public void register(Client client) throws Exception {
		log.info("Registering " + client.getName());
		em.persist(client);
		clientEventSrc.fire(client);
	}

	public Client add(Client client) throws Exception {
		register(client);
		return client;
	}

	public void remove(Client client) {
		em.remove(client);
	}

	public void update(Client client) {
		log.info("Registering " + client.getName());
		em.merge(client);
		clientEventSrc.fire(client);
	}
}
