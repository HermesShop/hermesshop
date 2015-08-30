package br.edu.unitri.pshop.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.unitri.pshop.model.User;

@Stateless
public class UserRegistration {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<User> userEventSrc;

	public void register(User user) throws Exception {
		log.info("Registering " + user.getName());
		em.persist(user);
		userEventSrc.fire(user);
	}
	
	public void update(User user) throws Exception {
		log.info("Registering " + user.getName());
		em.merge(user);
		userEventSrc.fire(user);
	}

	public User add(User user) throws Exception {
		register(user);
		return user;
	}

	public void remove(User user) {
		em.remove(user);
	}

	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}
}
