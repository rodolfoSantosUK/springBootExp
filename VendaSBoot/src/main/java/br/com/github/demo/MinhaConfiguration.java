package br.com.github.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


public class MinhaConfiguration {

    @Bean(name = "applicationName")
    public String applicationName() {
        return "applicationName";
    }

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("RODANDO A CONFIGURACAO DE DESENVOLVIMENTO " );
        };
    }

}
