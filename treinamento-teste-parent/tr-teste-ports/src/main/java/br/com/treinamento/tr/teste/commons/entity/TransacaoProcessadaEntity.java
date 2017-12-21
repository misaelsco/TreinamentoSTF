package br.com.treinamento.tr.teste.commons.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.treinamento.tr.teste.commons.dto.TransacaoDTO;
import br.com.treinamento.tr.teste.commons.enuns.TipoTransacaoEnum;
import br.com.treinamento.tr.teste.commons.util.JacksonJsonHelper;
import br.com.treinamento.tr.teste.commons.util.JsonDateTimeDeserializer;
import br.com.treinamento.tr.teste.commons.util.JsonDateTimeSerializer;

public class TransacaoProcessadaEntity{

	private Integer idConta;

	private Double valor;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private LocalDate dataEnvio;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private LocalDate dataProcessada;
	
	private TipoTransacaoEnum tipo;
	
	private TransacaoDTO transacao;
	
	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public LocalDate getDataProcessada() {
		return dataProcessada;
	}

	public void setDataProcessada(LocalDate dataProcessada) {
		this.dataProcessada = dataProcessada;
	}

	public TipoTransacaoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoTransacaoEnum tipo) {
		this.tipo = tipo;
	}

	public TransacaoDTO getTransacao() {
		return transacao;
	}

	public void setTransacao(TransacaoDTO transacao) {
		this.transacao = transacao;
	}

	public static void main(String[] args) {
		
		JacksonJsonHelper js = new JacksonJsonHelper();
		TransacaoProcessadaEntity tr = new TransacaoProcessadaEntity();
		
		tr.idConta = 1234;
		tr.valor = 150.00;
		tr.dataEnvio = LocalDate.now();
		tr.dataProcessada = LocalDate.now();
		tr.tipo = TipoTransacaoEnum.CREDITO;
		
		
	}

	
}