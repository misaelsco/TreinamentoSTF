package br.com.treinamento.tr.core.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinamento.tr.core.commons.dto.TransacaoDTO;
import br.com.treinamento.tr.core.port.outbound.DynamodbOutboundPort;
import br.com.treinamento.tr.core.port.outbound.SqsOutboundPort;

@Service
public class TreinamentoCoreService{
	
	private static final Logger log = LoggerFactory.getLogger(TreinamentoCoreService.class);	

	@Autowired
	private DynamodbOutboundPort dynamodbOutboundHandler;
	
	@Autowired
	private SqsOutboundPort sqsOutboundHandler;
	     

    public void processa(TransacaoDTO dto) throws Exception{
		
		log.info("Iniciando o processamento do {}.", "TreinamentoCoreHandler");
		

		try {
			
		    
			
		} catch (Exception e) {

			log.error(e.getMessage(), e);
		}
		
		log.info("Processamento do {} finalizado.", "TreinamentoCoreHandler");
		
	}

}