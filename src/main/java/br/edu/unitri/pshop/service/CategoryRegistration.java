package br.edu.unitri.pshop.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.unitri.pshop.model.Category;

@Stateless
public class CategoryRegistration {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Category> categoryEventSrc;

	public void register(Category category) throws Exception {
		log.info("Registering " + category.getDescription());
		em.persist(category);
		categoryEventSrc.fire(category);
	}

	public Category add(Category category) throws Exception {
		register(category);
		return category;
	}

	public void remove(Category category) {
		em.remove(category);
	}

	public void update(Category category) {
		log.info("Registering " + category.getName());
		em.merge(category);
		categoryEventSrc.fire(category);
	}
}
