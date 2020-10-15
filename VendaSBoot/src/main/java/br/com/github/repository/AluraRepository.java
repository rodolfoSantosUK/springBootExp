package br.com.github.repository;


import br.com.github.alura.modelo.Conta;
import br.com.github.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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


    public void metodoExemplo() {

        String jpql = " select c from Conta c  ";
        TypedQuery<Conta> query = entityManager.createQuery(jpql, Conta.class);
        List<Conta> contas = query.getResultList();
        contas.forEach(System.out::println);
    }




}
