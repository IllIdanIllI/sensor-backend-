package com.sensor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericDao<T extends Serializable, N> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> persistentClass;

    public GenericDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            throw new IllegalStateException("Session has not been set on DAO before usage");
        }
        return session;
    }

    @Transactional
    public T findById(long id) {
        return getSession().find(persistentClass, id);
    }

    @Transactional
    public List<T> findEntitiesPagination(int currentPage, int limit) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistentClass);
        Root<T> root = query.from(persistentClass);
        query.select(root);
        Query<T> tQuery = session.createQuery(query)
                .setFirstResult(currentPage * limit)
                .setMaxResults(limit);
        return tQuery.getResultList();
    }

    @Transactional
    public Long findEntitiesAmountPagination() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.TYPE);
        Root<T> root = query.from(persistentClass);
        query.select(builder.count(root.get("id")));
        Query countQuery = session.createQuery(query);
        return (Long) countQuery.getSingleResult();
    }

    @Transactional
    public Long save(T entity) {
        return (Long) getSession().save(entity);
    }

    @Transactional
    public void delete(T entity) {
        getSession().delete(entity);
    }
}
