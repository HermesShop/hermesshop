/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.edu.unitri.pshop.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.unitri.pshop.model.Client;

@ApplicationScoped
public class ClientRepository {

    @Inject
    private EntityManager em;

    @SuppressWarnings("unchecked")
	public List<Client> findAll() {
    	return em.createQuery("from Client").getResultList();
    }
    
    public Client findById(Long id) {
        return em.find(Client.class, id);
    }
    
    public Client findByEmailAndPassword(String email, String password) {
		return (Client) em
				.createQuery(
						"from Client c where c.email = :email and c.password = :password")
				.setParameter("email", email).setParameter("password", password).getSingleResult();
    }

    public Client findByTitle(String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
        Root<Client> client = criteria.from(Client.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(client).where(cb.equal(client.get(Client_.name), email));
        criteria.select(client).where(cb.equal(client.get("title"), title));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Client> findAllOrderedByDescription() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
        Root<Client> client = criteria.from(Client.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(client).orderBy(cb.asc(client.get(Client_.name)));
        criteria.select(client).orderBy(cb.asc(client.get("description")));
        return em.createQuery(criteria).getResultList();
    }
}
