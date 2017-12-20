package br.com.treinamento.tr.teste.starter.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;


@Configuration
@EnableJms
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.treinamento.tr.teste")
public class TreinamentoTestConfiguration {


}
