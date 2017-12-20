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
	 private <T> List<Object> carregaMassaTransacoes(String json, final TypeReference<T> clazz) {
	        List<Object> list = new ArrayList<>();
	        try {
	        	list
	                    .addAll((new ObjectMapper().readValue(json, clazz)));

	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	        
	        return list;
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
