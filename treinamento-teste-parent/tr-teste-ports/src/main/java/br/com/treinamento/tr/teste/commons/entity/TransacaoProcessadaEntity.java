package br.com.treinamento.tr.teste.commons.entity;

import java.time.LocalDate;

import com.amazonaws.util.json.Jackson;

import br.com.treinamento.tr.teste.commons.dto.TransacaoDTO;
import br.com.treinamento.tr.teste.commons.enuns.TipoTransacaoEnum;


public class TransacaoProcessadaEntity{

	private Integer idConta;

	private Double valor;
	
	private LocalDate dataEnvio;

	private LocalDate dataProcessada;
	
	private TipoTransacaoEnum tipo;
	
	private TransacaoDTO transacao;
	
	
	
	public static void main(String[] args) {
		
		JacksonJsonHelper js = new JacksonJsonHelper();
		TransacaoProcessadaEntity tr = new TransacaoProcessadaEntity();
		
		tr.idConta = 1234;
		tr.valor = 150.00;
		tr.dataEnvio = LocalDate.now();
		tr.dataProcessada = LocalDate.now();
		tr.tipo = TipoTransacaoEnum.CREDITO;
		
		System.out.println(js.writeWithDefaultDateFormat(tr));
		
		//Jackson.toJsonString(tr);
	}



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
	
	
	
}