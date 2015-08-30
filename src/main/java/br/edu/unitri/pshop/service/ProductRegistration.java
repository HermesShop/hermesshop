package br.edu.unitri.pshop.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.unitri.pshop.model.Product;

@Stateless
public class ProductRegistration {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Product> productEventSrc;

	public void register(Product product) throws Exception {
		log.info("Registering " + product.getDescription());
		em.persist(product);
		productEventSrc.fire(product);
	}
	
	public void update(Product product) throws Exception {
		log.info("Registering " + product.getDescription());
		em.merge(product);
		productEventSrc.fire(product);
	}

	public Product add(Product product) throws Exception {
		register(product);
		return product;
	}

	public void remove(Product product) {
		em.remove(product);
	}
}
