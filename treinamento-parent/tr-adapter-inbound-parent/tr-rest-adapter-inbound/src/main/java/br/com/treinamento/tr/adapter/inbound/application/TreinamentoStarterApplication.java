package br.com.treinamento.tr.adapter.inbound.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.treinamento.tr")
@EnableJms
public class TreinamentoStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreinamentoStarterApplication.class, args);
    }
}
