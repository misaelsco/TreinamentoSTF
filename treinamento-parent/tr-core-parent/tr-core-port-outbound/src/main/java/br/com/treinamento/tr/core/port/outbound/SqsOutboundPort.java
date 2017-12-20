package br.com.treinamento.tr.core.port.outbound;

import br.com.treinamento.tr.core.commons.dto.TransacaoProcessadaDTO;

public interface SqsOutboundPort {
	
	public void enviarParaSQS(TransacaoProcessadaDTO dto) throws Exception;
	

}