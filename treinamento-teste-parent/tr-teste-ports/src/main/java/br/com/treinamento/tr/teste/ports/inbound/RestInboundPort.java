package br.com.treinamento.tr.teste.ports.inbound;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.treinamento.tr.teste.commons.dto.TransacaoProcessadaDTO;

public interface RestInboundPort {
	
	ResponseEntity<List<TransacaoProcessadaDTO>> consultaExtrato(Integer idConta, LocalDate dataInicial, LocalDate dataFinal);

}
