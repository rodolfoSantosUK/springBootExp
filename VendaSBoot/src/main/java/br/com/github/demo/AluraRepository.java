package br.com.github.demo;


import br.com.github.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class AluraRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void salvar(Cliente cliente) {
        entityManager.persist(cliente);
    }

}
