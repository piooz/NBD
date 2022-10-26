package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
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
        }
        em.getTransaction().commit();
    }

    public List<T> findByQuery(CriteriaQuery<T> query) {
        List<T> list;

        em.getTransaction().begin();
        list = em.createQuery(query).setLockMode(LockModeType.OPTIMISTIC).getResultList();
        em.getTransaction().commit();

        return list;
    }

    public abstract List<T> findAll();
    public abstract T getById(long Id);
}
