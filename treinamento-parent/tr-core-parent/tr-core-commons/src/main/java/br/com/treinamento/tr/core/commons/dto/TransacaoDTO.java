package br.com.treinamento.tr.core.commons.dto;

import java.time.LocalDate;

import br.com.treinamento.tr.core.commons.enumeration.TipoTransacaoEnum;

public class TransacaoDTO{
	
	private Integer idConta;
	private Double valor;
	private LocalDate dataEnvio;
	private TipoTransacaoEnum tipo;

	
}