package br.com.treinamento.tr.teste.starter.consultar.extrato;

import static org.junit.Assert.fail;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;

import br.com.treinamento.tr.teste.commons.dto.TransacaoProcessadaDTO;
import br.com.treinamento.tr.teste.commons.entity.TransacaoProcessadaEntity;
import br.com.treinamento.tr.teste.ports.inbound.RestInboundPort;
import br.com.treinamento.tr.teste.ports.outbound.DynamoOutboundPort;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;

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

	@Dado("^que exista a seguinte massa de dados cadastrada$")
	public void dadoQueExistaASeguinte(String nomeArquivo)  {
		dynamoPort.insereListatransacoes(transacoesDynamo);
	}

	@E("^o filtro selecionado seja $")
	public void oFiltroSelecionadoSeja(Integer idConta, LocalDate dataInicial, LocalDate dataFinal) {
		idContaGlobal = idConta;
		dataInicialGlobal = dataInicial;
		dataFinalGlobal = dataFinal;
	}

	public void fazRequisicao() {
		transacoesRetornadas = restPort.consultaExtrato(idContaGlobal, dataInicialGlobal, dataFinalGlobal);
	}
	
	public void entaoDeveRetornarAsTransacoes() {
		transacoesDynamo.stream()
			.filter(td -> td.getIdConta().equals(idContaGlobal))
			.filter(td -> td.getDataProcessada().getMonth().equals(dataInicialGlobal))
		
		
		
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
