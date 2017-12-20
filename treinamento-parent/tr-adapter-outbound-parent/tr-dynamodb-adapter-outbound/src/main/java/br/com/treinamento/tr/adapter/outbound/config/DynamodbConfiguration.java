package br.com.treinamento.tr.adapter.outbound.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import br.com.treinamento.tr.adapter.outbound.entity.TransacaoProcessadaEntity;

@Configuration("outboundDynamodb")
public class DynamodbConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DynamodbConfiguration.class);
    
    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;
    
    @Value("${aws.region}")
    private String region;
    
    @Value("${aws.dynamodb.endpoint.use}")
    private Boolean dynamodbUseEndpoint;

    @Value("${aws.dynamodb.endpoint.host}")
    private String dynamodbHostEndPoint;

    @Value("${aws.dynamodb.endpoint.port}")
    private Integer dynamodbPortEndPoint;
    
    
    @Value("${aws.dynamodb.outbound.table.name}")
    private String dynamoTableName;
    
  
 }