package br.edu.unitri.pshop.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.unitri.pshop.model.Profile;

@Stateless
public class ProfileRegistration {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Profile> profileEventSrc;

	public void register(Profile profile) throws Exception {
		log.info("Registering " + profile.getDescription());
		em.persist(profile);
		profileEventSrc.fire(profile);
	}
	
	public void update(Profile profile) throws Exception {
		log.info("Registering " + profile.getDescription());
		em.merge(profile);
		profileEventSrc.fire(profile);
	}

	public Profile add(Profile profile) throws Exception {
		register(profile);
		return profile;
	}

	public void remove(Profile profile) {
		em.remove(profile);
	}
}
