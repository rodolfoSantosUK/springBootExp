package br.com.github.repository;


import br.com.github.alura.modelo.Conta;
import br.com.github.alura.modelo.MediaComData;
import br.com.github.alura.modelo.Movimentacao;
import br.com.github.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    public void somandoValores() {

        String jpql = " select sum(m.valor) from Movimentacao m ";
        TypedQuery<BigDecimal> query = entityManager.createQuery(jpql, BigDecimal.class);
        BigDecimal soma = query.getSingleResult();
        System.out.println("Soma " + soma);
    }

    public void mediaValores() {

        String jpql = " select avg(m.valor) from Movimentacao m ";
        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
        Double media = query.getSingleResult();
        System.out.println("Media " + media);
    }

    public void mediaValoresPorData() {

        String jpql = " select avg(m.valor) from Movimentacao m group by day(m.data), month(m.data), year(m.data)";
        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
        List<Double> media = query.getResultList();
        System.out.println("Media " + media);
    }

    public void mediaValoresAgrupadosPorData() {

        String jpql = " select new br.com.github.alura.modelo.MediaComData( avg(m.valor), day(m.data), month(m.data)) " +
                "from Movimentacao m group by day(m.data), month(m.data), year(m.data)";

        TypedQuery<MediaComData> query = entityManager.createQuery(jpql, MediaComData.class);
        List<MediaComData> media = query.getResultList();

        for (MediaComData resultado : media) {
            System.out.println(" A media das movimentacoes do dia " + resultado.getDia() + "/" + resultado.getMes() + " é " + resultado.getValor());
        }
    }


    public void somaDasMovimentacoes() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);

        // Objeto principal
        Root<Movimentacao> root = query.from(Movimentacao.class);


        // CLausulas
        Expression<BigDecimal> soma = builder.sum(root.<BigDecimal>get("valor"));
        query.select(soma);

        TypedQuery<BigDecimal> typedQuery = entityManager.createQuery(query);
        System.out.println(" A soma das movimentações é:  " + typedQuery.getSingleResult());

    }

    public List<Movimentacao> getMovimentacaoFiltradaPorData(Integer dia, Integer mes, Integer ano) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movimentacao> query = builder.createQuery(Movimentacao.class);

        Root<Movimentacao> root = query.from(Movimentacao.class);

        List<Predicate> predicates = new ArrayList<>();

        if (dia != null) {
            Predicate predicate = builder.equal(builder.function("day", Integer.class, root.get("data")), dia);
            predicates.add(predicate);
        }

        if (mes != null) {
            Predicate predicate = builder.equal(builder.function("month", Integer.class, root.get("data")), mes);
            predicates.add(predicate);
        }

        if (ano != null) {
            Predicate predicate = builder.equal(builder.function("year", Integer.class, root.get("data")), ano);
            predicates.add(predicate);
        }

        query.where((Predicate[]) predicates.toArray(new Predicate[0]));
        TypedQuery<Movimentacao> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();

    }

}
