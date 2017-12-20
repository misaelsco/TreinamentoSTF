package br.com.treinamento.tr.teste.starter.consultar.extrato;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(
		        format = { "pretty", "html:target/cucumber" }, 
                monochrome = true, 
                glue = {"br.com.treinamento.tr.teste.starter.consultar.extrato"}, 
                features = {"src/test/resources/ConsultaExtrato/Treinamento.feature"}, 
                plugin = "br.com.treinamento.tr.teste.starter.utils.ExtentCucumberFormatter", 
                snippets = cucumber.api.SnippetType.CAMELCASE)
public class ConsultarExtratoRunner {
}
