package br.com.treinamento.tr.core.port.outbound;

import java.time.LocalDate;

import br.com.treinamento.tr.core.commons.dto.TransacaoProcessadaDTO;

public interface DynamodbOutboundPort {
	
	
	public void consultarExtrato(Integer idConta, LocalDate dataInicial, LocalDate dataFinal);

	void enviarParaDynamoDB(TransacaoProcessadaDTO dto) throws Exception;
	
}