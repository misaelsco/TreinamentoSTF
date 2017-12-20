package br.com.treinamento.tr.teste.starter.utils;

import static org.junit.Assert.fail;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;

import br.com.treinamento.tr.teste.commons.dto.TransacaoDTO;

public class FileUtils {

    private static final String JSON_FILES_PATH = "./src/test/resources/Path/json/";
	
    
    List<TransacaoDTO> transacoes;
	 private void carregaMassaTransacoes(String nomeArquivo) {
	        transacoes = new ArrayList<>();
	        try {
	            transacoes
	                    .addAll((new ObjectMapper().readValue(retornaConteudoArquivoJson(nomeArquivo),
	                            new TypeReference<List<TransacaoDTO>>() {
	                            })));

	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }
	 
	 private String retornaConteudoArquivoJson(String fileName) {
	        JsonParser parser = new JsonParser();
	        try {
	            Object json = parser.parse(new FileReader(JSON_FILES_PATH + fileName));
	            return json.toString();
	        } catch (Exception e) {
	        }
	        return null;
	    }

}
