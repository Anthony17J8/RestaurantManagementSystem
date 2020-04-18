package ru.icoltd.rvs.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.Optional;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private final Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.of(em.find(entityClass, id));
    }

    @Override
    public Iterable<T> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        return em.createQuery(query.select(query.from(entityClass))).getResultList();
    }

    @Override
    public T makePersistent(T entity) {
        return em.merge(entity);
    }


    @Override
    public void remove(T entity) {
        em.remove(em.merge(entity));
    }
}
