package br.com.github.repository;


import br.com.github.in28.modelo.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    @Transactional
    public Course save(Course course) {

        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }

        return course;

    }

    public Course findById(Integer id) {
        Course courseFromDatabase = em.find(Course.class, id);
        return courseFromDatabase;
    }

    @Transactional
    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    @Transactional
    public void brincandoComEntityManager(Course course) {
        em.persist(course);
        em.flush();
        course.setName("Rodolfo Santos atualizado");
        logger.info(course.getName());
    }

    @Transactional
    public void usandoRefresh(Course course) {
        em.persist(course);
        logger.info(" Course no banco " + course.getName());
        course.setName(" Alterando no java : " + course.getName());
        logger.info(" Course no java: " + course.getName());
        em.flush();
        logger.info("Depois do flush : " + course.getName());
        course.setName(" Alterando outra vez no java : " + course.getName());
        em.refresh(course);
        logger.info("Depois do refresh " + course.getName());
        logger.info(" Course no java " + course.getName());
        course.setName("merge do java pro banco");
        em.merge(course);
        logger.info(course.getName());
    }

    

}
