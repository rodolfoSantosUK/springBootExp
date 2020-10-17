package br.com.github.demo;

import br.com.github.alura.modelo.Conta;
import br.com.github.alura.modelo.Movimentacao;
import br.com.github.alura.modelo.TipoMovimentacao;
import br.com.github.in28.modelo.Course;
import br.com.github.in28.modelo.Passport;
import br.com.github.in28.modelo.Student;
import br.com.github.modelo.Cliente;
import br.com.github.modelo.Pedido;
import br.com.github.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@ComponentScan({"br.*"})
@EntityScan(basePackages = {"br.com.github.modelo",
        "br.com.github.alura",
        "br.com.github.in28.modelo"})
@EnableJpaRepositories("br.com.github.repository")
public class VendasApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AluraRepository aluraRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PassportRepository passportRepository;

    public void testeCursoAlura() {
        Conta conta = new Conta();
        conta.setAgencia(1);
        conta.setSaldo(22.9);
        conta.setNumero(1);
        conta.setTitular("Rodolfo Santos");

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setData(LocalDateTime.now().plusDays(1));
        movimentacao.setConta(conta);
        movimentacao.setDescricao("MOvimentacao 1");
        movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
        movimentacao.setValor(BigDecimal.valueOf(150));

        Movimentacao movimentacao2 = new Movimentacao();
        movimentacao2.setData(LocalDateTime.now());
        movimentacao2.setConta(conta);
        movimentacao2.setDescricao("MOvimentacao 1");
        movimentacao2.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
        movimentacao2.setValor(BigDecimal.valueOf(150));

        System.out.println("Salvando conta");
        aluraRepository.salvar(conta);

        System.out.println("Salvando movimentacao");
        aluraRepository.salvar(movimentacao);
        aluraRepository.salvar(movimentacao2);

        // aluraRepository.metodoExemplo();
        aluraRepository.somandoValores();
        aluraRepository.mediaValores();
        aluraRepository.mediaValoresPorData();
        aluraRepository.mediaValoresAgrupadosPorData();
        aluraRepository.somaDasMovimentacoes();

        List<Movimentacao> movimentacoes = aluraRepository.getMovimentacaoFiltradaPorData(16, null, null);
        System.out.println(movimentacoes);

    }


    public void testeCursoIn28() {

        Course course = new Course();
        course.setName("Linux");

        courseRepository.save(course);

        Course courseFromDatabase = courseRepository.findById(1L);
        logger.info("Course on database: " + courseFromDatabase);

       // courseRepository.usandoRefresh(new Course("Rodolfo"));

    }

    public void testeStudentPassport() {

        Passport passport = new Passport("Z43423");
        passportRepository.save(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        studentRepository.save(student);

    }




    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        testeCursoAlura();
      //  testeCursoIn28();
        testeStudentPassport();
    }
}
