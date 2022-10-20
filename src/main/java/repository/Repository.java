package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;

import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {

    protected EntityManager em;

    public Repository(EntityManager entityManager) {
        em = entityManager;
    }

    public void add(T object) {
        em.getTransaction().begin();
        if(em.contains(object)) {
            em.merge(object);
        } else {
            em.persist(object);
        }
        em.getTransaction().commit();
    }

    public void remove(T object) {
        em.getTransaction().begin();
        if(em.contains(object)){
            em.remove(object);
        } else {
            em.merge(object);
        }
        em.getTransaction().commit();
    }

    public abstract List<T> findAll();
    public abstract T getById(long Id);
}
