package br.com.treinamento.tr.adapter.inbound.handler;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinamento.tr.core.commons.dto.TransacaoDTO;
import br.com.treinamento.tr.core.commons.dto.TransacaoProcessadaDTO;
import br.com.treinamento.tr.core.port.inbound.RestInboundPort;


@RestController
@RequestMapping("/transacoes")
public class RestConsultarExtratoHandler {

    private static final Logger log = LoggerFactory.getLogger(RestConsultarExtratoHandler.class);	
    

    @Autowired
    private RestInboundPort restInboundPort;
    
    
    @RequestMapping(value = "/{id}/{dataInicial}/{dataFinal}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<TransacaoProcessadaDTO>> consultarTransacoes(
    		@PathVariable("id") Integer id, 
    		@PathVariable("dataInicial") LocalDate dataInicial, 
    		@PathVariable("dataFinal") LocalDate dataFinal) 
    				throws Exception {
        log.info("recebendo requisição buscarEstabelecimento id{}", id);
        List<TransacaoProcessadaDTO> response = restInboundPort.consultarTransacoes(id, dataInicial, dataFinal);

        log.info("CadastroTecnicoInboundHandler::buscarEstabelecimento()::retorno rest: {} ", response);
        return retornaMensagemSucesso(response);
    }

    @RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> adicionarEstabelecimento(@RequestBody TransacaoDTO transacao) {
        
        List<TransacaoProcessadaDTO> response = restInboundPort.salvarTransacao(transacao);

        log.info("retorno rest: {} ", response);
        return retornaMensagemSucesso(response);
    }

    private ResponseEntity<List<TransacaoProcessadaDTO>> retornaMensagemSucesso(List<TransacaoProcessadaDTO> response) {
        return new ResponseEntity<List<TransacaoProcessadaDTO>>(response, HttpStatus.OK);
    }


}