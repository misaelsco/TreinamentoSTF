package br.com.treinamento.tr.teste.ports.outbound;

import java.util.List;

import br.com.treinamento.tr.teste.commons.entity.TransacaoProcessadaEntity;

public interface DynamoOutboundPort {
	
	void insereListatransacoes(List<TransacaoProcessadaEntity> listaTransacoes);
	
	/**
	 * Deletar todos os registros do dynamo
	 */
	void tearDown();

}
