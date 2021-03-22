package spittr.db.jpa;

import org.springframework.stereotype.Repository;
import spittr.db.SpitterRepository;
import spittr.domain.Spitter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaSpitterRepository implements SpitterRepository {

    @PersistenceContext
    private EntityManager entityManager;
    //并不会真正注入EntityManager，因为它不是线程安全的，
    //注入的是一个代理，通过代理关联到当前事务上下文的真正的EntityManager

//    @PersistenceUnit
//    private EntityManagerFactory emf;
//    emf.createEntityManager();

    public long count() {
        return findAll().size();
    }

    public Spitter save(Spitter spitter) {
        entityManager.persist(spitter);
        return spitter;
    }

    public Spitter findOne(long id) {
        return entityManager.find(Spitter.class, id);
    }

    public Spitter findByUsername(String username) {
        return (Spitter) entityManager.createQuery("select s from Spitter s where s.username=?").setParameter(1, username).getSingleResult();
    }

    public List<Spitter> findAll() {
        return (List<Spitter>) entityManager.createQuery("select s from Spitter s").getResultList();
    }

}
