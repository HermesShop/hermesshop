package br.edu.unitri.pshop.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.unitri.pshop.model.Client;
import br.edu.unitri.pshop.model.Member;

@Stateless
public class ClientRegistration {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Member> memberEventSrc;

	public void register(Member member) throws Exception {
		log.info("Registering " + member.getName());
		em.persist(member);
		memberEventSrc.fire(member);
	}

	public Client add(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

}
