package br.com.treinamento.tr.core.port.inbound;

import java.time.LocalDate;
import java.util.List;

import br.com.treinamento.tr.core.commons.dto.TransacaoDTO;
import br.com.treinamento.tr.core.commons.dto.TransacaoProcessadaDTO;

public interface RestInboundPort {
	

	
	public List<TransacaoProcessadaDTO> consultarTransacoes(Integer id, LocalDate dataInicial, LocalDate dataFinal);

	public List<TransacaoProcessadaDTO> salvarTransacao(TransacaoDTO transacao);
	

}