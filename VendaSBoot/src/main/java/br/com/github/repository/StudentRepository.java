package br.com.github.repository;

import br.com.github.in28.modelo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    @Transactional
    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public Student findById(Integer id) {
        Student studentFromDatabase = em.find(Student.class, id);
        return studentFromDatabase;
    }

    @Transactional
    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }



}
