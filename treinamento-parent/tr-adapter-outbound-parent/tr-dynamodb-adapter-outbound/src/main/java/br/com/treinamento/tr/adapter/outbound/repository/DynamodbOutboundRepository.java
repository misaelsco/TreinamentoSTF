package br.com.treinamento.tr.adapter.outbound.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Qualifier;


import br.com.treinamento.tr.core.commons.dto.TransacaoProcessadaDTO;
import br.com.treinamento.tr.adapter.outbound.entity.TransacaoProcessadaEntity;

@Repository("dynamoDbOutboundRepositoryTest")
public class DynamodbOutboundRepository{
    
    private static final Logger log = LoggerFactory.getLogger(DynamodbOutboundRepository.class);


    @Autowired
    @Qualifier("dynamoDbOutboundClient")

    public void enviarParaDynamoDB(TransacaoProcessadaDTO dto) throws Exception{
        TransacaoProcessadaEntity entity = new TransacaoProcessadaEntity(); 
        
        
    }
    
}