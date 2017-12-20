package br.com.treinamento.tr.teste.integrado.adapter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class IntegradoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(IntegradoConfiguration.class);
    //AWS
    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;
    
    @Value("${aws.region}")
    private String region;

    //DynamoDB
    @Value("${aws.dynamodb.endpoint.use}")
    private Boolean dynamodbUseEndpoint;

    @Value("${aws.dynamodb.endpoint.host}")
    private String dynamodbHostEndPoint;

    @Value("${aws.dynamodb.endpoint.port}")
    private Integer dynamodbPortEndPoint;
    
    @Value("${aws.dynamodb.outbound.table.name}")
    private String dynamoOutboundTableName;
    

    //SQS
    @Value("${aws.sqs.endpoint.use}")
    private boolean sqsUseEndpoint;
    
    @Value("${aws.sqs.endpoint.host}")
    private String sqsHost;
    
    @Value("${aws.sqs.endpoint.port}")
    private Integer sqsPort;
    
    @Value("${aws.sqs.outbound.queue.name}")
    private String sqsOutboundQueue;
    



}
