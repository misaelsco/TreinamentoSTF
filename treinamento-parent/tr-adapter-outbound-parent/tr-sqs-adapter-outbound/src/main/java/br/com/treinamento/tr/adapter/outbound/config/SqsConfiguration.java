package br.com.treinamento.tr.adapter.outbound.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration("outboundSqs")
public class SqsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SqsConfiguration.class);
    
    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;
    
    @Value("${aws.region}")
    private String region;
    
    @Value("${aws.sqs.endpoint.use}")
    private boolean sqsUseEndpoint;

    @Value("${aws.sqs.endpoint.host}")
    private String sqsHost;

    @Value("${aws.sqs.endpoint.port}")
    private Integer sqsPort;
        
    @Value("${aws.sqs.queue.concurrency}")
    private String concurrency;

    @Value("${aws.sqs.queue.visibility.timeout}")
    private Integer visibilityTimeout;

    @Value("${aws.sqs.queue.wait.time.seconds}")
    private Integer waitTimeSeconds;

    @Value("${aws.sqs.queue.number.messages.receive}")
    private Integer numberOfMessagesToReceive;

    
    @Value("${aws.sqs.outbound.queue.name}")
    private String sqsQueueOutbound;
    

    
    
 }