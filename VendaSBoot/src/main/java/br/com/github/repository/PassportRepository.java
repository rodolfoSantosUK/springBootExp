package br.com.github.repository;

import br.com.github.in28.modelo.Passport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class PassportRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Passport findById(Long id) {
        return em.find(Passport.class, id);
    }

    @Transactional
    public Passport save(Passport passport) {
        if (passport.getId() == null) {
            em.persist(passport);
        } else {
            em.merge(passport);
        }
        return passport;
    }

    public Passport findById(Integer id) {
        Passport passportFromDatabase = em.find(Passport.class, id);
        return passportFromDatabase;
    }

    @Transactional
    public void deleteById(Long id) {
        Passport passport = findById(id);
        em.remove(passport);
    }

  

}
