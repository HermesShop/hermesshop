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

import br.edu.unitri.pshop.model.ItemOrder;

@ApplicationScoped
public class ItemOrderRepository {

    @Inject
    private EntityManager em;

    @SuppressWarnings("unchecked")
	public List<ItemOrder> findAll() {
    	return em.createQuery("from ItemOrder").getResultList();
    }
    
    public ItemOrder findById(Long id) {
        return em.find(ItemOrder.class, id);
    }

    public ItemOrder findByTitle(String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemOrder> criteria = cb.createQuery(ItemOrder.class);
        Root<ItemOrder> itemOrder = criteria.from(ItemOrder.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(itemOrder).where(cb.equal(itemOrder.get(ItemOrder_.name), email));
        criteria.select(itemOrder).where(cb.equal(itemOrder.get("title"), title));
        return em.createQuery(criteria).getSingleResult();
    }
    
    public ItemOrder findByOrderId(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemOrder> criteria = cb.createQuery(ItemOrder.class);
        Root<ItemOrder> itemOrder = criteria.from(ItemOrder.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(itemOrder).where(cb.equal(itemOrder.get(ItemOrder_.name), email));
        criteria.select(itemOrder).where(cb.equal(itemOrder.get("order.id"), id));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<ItemOrder> findAllItemOrderedByDescription() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemOrder> criteria = cb.createQuery(ItemOrder.class);
        Root<ItemOrder> itemOrder = criteria.from(ItemOrder.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(itemOrder).itemOrderBy(cb.asc(itemOrder.get(ItemOrder_.name)));
        criteria.select(itemOrder).orderBy(cb.asc(itemOrder.get("description")));
        return em.createQuery(criteria).getResultList();
    }
}
