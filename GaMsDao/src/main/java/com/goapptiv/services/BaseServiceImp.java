package com.goapptiv.services;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;

public abstract class BaseServiceImp<M> {

    /**
     * EntityManager
     */
    private final EntityManager em;

    /**
     * Constructor
     */
    BaseServiceImp(EntityManager em) {
        this.em = em;
    }

    /**
     * Get EntityManager
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * Get base query
     *
     * @param entity (required) JPAQuery instance
     * @return JPAQuery for Linked Entity
     */
    JPAQuery<M> getBaseQuery(EntityPathBase entity) {
        JPAQuery<M> query = new JPAQuery<M>(this.em);
        query.from(entity);
        return query;
    }
}