package br.com.treinamento.tr.adapter.outbound.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import br.com.treinamento.tr.core.commons.dto.TransacaoProcessadaDTO;

import br.com.treinamento.tr.core.port.outbound.SqsOutboundPort;

@Service
public class SqsAdapterOutboundHandler implements SqsOutboundPort{

    private static final Logger log = LoggerFactory.getLogger(SqsAdapterOutboundHandler.class);	

	@Override
	public void enviarParaSQS(TransacaoProcessadaDTO dto) throws Exception{
		
		

	}

}