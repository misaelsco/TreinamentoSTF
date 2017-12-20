package br.com.treinamento.tr.teste.starter.consultar.extrato;

import static org.junit.Assert.fail;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;

import br.com.treinamento.tr.teste.commons.dto.TransacaoProcessadaDTO;
import br.com.treinamento.tr.teste.commons.entity.TransacaoProcessadaEntity;
import br.com.treinamento.tr.teste.ports.inbound.RestInboundPort;
import br.com.treinamento.tr.teste.ports.outbound.DynamoOutboundPort;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class ConsultaExtratoSteps {
	private static final String JSON_FILES_PATH = "./src/test/resources/ConsultaExtrato/json/";

	@Autowired
	private DynamoOutboundPort dynamoPort;

	@Autowired
	private RestInboundPort restPort;

	private List<TransacaoProcessadaEntity> transacoesDynamo;

	private Integer idContaGlobal;

	private LocalDate dataInicialGlobal;

	private LocalDate dataFinalGlobal;

	private ResponseEntity<List<TransacaoProcessadaDTO>> transacoesRetornadas;

	private List<TransacaoProcessadaEntity> transacoesEsperadas;

	@Dado("^que exista a seguinte massa de dados cadastrada \"([^\"]*)\"$")
	public void queExistaASeguinteMassaDeDadosCadastrada(String arg1) throws Throwable {
		dynamoPort.insereListatransacoes(transacoesDynamo);
	}

	@Dado("^o filtro selecionado seja \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
	public void oFiltroSelecionadoSejaE(Integer idConta, LocalDate dataInicial, LocalDate dataFinal) throws Throwable {
		idContaGlobal = idConta;
		dataInicialGlobal = dataInicial;
		dataFinalGlobal = dataFinal;
	}

	@Quando("^uma requisicao de extrato for solicitada$")
	public void umaRequisicaoDeExtratoForSolicitada() throws Throwable {
		transacoesRetornadas = restPort.consultaExtrato(idContaGlobal, dataInicialGlobal, dataFinalGlobal);
	}

	@Entao("^devera retornar as transacoes para o periodo selecionado$")
	public void deveraRetornarAsTransacoesParaOPeriodoSelecionado() throws Throwable {
		
		transacoesEsperadas = transacoesDynamo.stream()
			.filter(td -> td.getIdConta().equals(idContaGlobal))
			.filter(td -> td.getDataProcessada().isAfter(dataInicialGlobal))
			.filter(td -> td.getDataProcessada().isBefore(dataFinalGlobal))
			.collect(Collectors.toList());
		
	}

	@Dado("^o filtro selecionado seja \"([^\"]*)\"$")
	public void oFiltroSelecionadoSeja(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Dado("^que exista a seguinte massa de dados cadastrada:$")
	public void queExistaASeguinteMassaDeDadosCadastrada(DataTable arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		throw new PendingException();
	}



































	private void carregaMassaTransacoes(String nomeArquivo) {
		transacoesDynamo = new ArrayList<>();
		try {
			transacoesDynamo
			.addAll((new ObjectMapper().readValue(retornaConteudoArquivoJson(nomeArquivo),
					new TypeReference<List<TransacaoProcessadaEntity>>() {
			})));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	private String retornaConteudoArquivoJson(String fileName) {
		JsonParser parser = new JsonParser();
		try {
			Object json = parser.parse(new FileReader(JSON_FILES_PATH + fileName));
			return json.toString();
		} catch (Exception e) {
		}
		return null;
	}

}
