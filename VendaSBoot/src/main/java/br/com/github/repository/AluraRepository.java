package br.com.github.repository;


import br.com.github.alura.modelo.Conta;
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
    public void salvar(Object obj) {
        entityManager.persist(obj);
    }

    public Conta conta() {
        return entityManager.find(Conta.class, 1L);
    }




}
