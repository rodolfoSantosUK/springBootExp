package br.com.github.br.com.github.demo;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MinhaConfiguration {

    @Bean(name = "applicationName")
    public String applicationName() {
        return "applicationName";
    }

}
