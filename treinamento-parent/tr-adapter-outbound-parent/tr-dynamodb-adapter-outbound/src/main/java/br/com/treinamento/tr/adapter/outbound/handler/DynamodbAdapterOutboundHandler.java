package br.com.treinamento.tr.adapter.outbound.handler;

import java.time.LocalDate;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinamento.tr.adapter.outbound.entity.TransacaoProcessadaEntity;
import br.com.treinamento.tr.adapter.outbound.repository.DynamodbOutboundRepository;
import br.com.treinamento.tr.core.commons.dto.TransacaoProcessadaDTO;
import br.com.treinamento.tr.core.port.outbound.DynamodbOutboundPort;
@Service
public class DynamodbAdapterOutboundHandler implements DynamodbOutboundPort{


	@Autowired
	private DynamodbOutboundRepository dynamodbOutboundRepository;
	
	@Override
	public void enviarParaDynamoDB(TransacaoProcessadaDTO dto) throws Exception{
	
		
		TransacaoProcessadaEntity entity = new TransacaoProcessadaEntity(); 
		System.out.println("Enviando para o dynamo" + entity );
		
		try {
			
			
		} catch (Exception e) {
			System.out.println("Erro");
		}
	
	}

	@Override
	public void consultarExtrato(Integer idConta, LocalDate dataInicial, LocalDate dataFinal) {
		// TODO Auto-generated method stub
		
	}

}
