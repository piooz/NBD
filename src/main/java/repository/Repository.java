package repository;

import jakarta.persistence.EntityManager;

public abstract class Repository<T> {

    private EntityManager em;

    Repository(EntityManager entityManager) {
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

    abstract public T getById(int Id);
}
