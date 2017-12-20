package br.com.treinamento.tr.teste.commons.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JacksonJsonHelper {

    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final String MSG_ERRO = "Erro ao desserializar massa de dados";

    private static final Logger logger = LoggerFactory.getLogger(JacksonJsonHelper.class);

    public final <T> T readAsObject(final String json, final Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(MSG_ERRO, e);
        }
    }

    public final <T> T readAsObjectWithDefaultDateFormat(final String json, final Class<T> clazz) {
        try {
            final SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(df);

            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(MSG_ERRO, e);
        }
    }

    public final <T> T readAsObjectWithDefaultDateFormatFromFilePath(final String path, final Class<T> clazz) {
        try {
            final SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(df);

            return mapper.readValue(new URL(path), clazz);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(MSG_ERRO, e);
        }
    }

    public final <T> T readAsObjectWithDefaultDateFormat(final String json, final TypeReference<T> clazz) {
        try {
            final SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(df);

            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(MSG_ERRO, e);
        }
    }

    public final <T> T readAsObjectWithDefaultDateFormat(final File jsonFile, final Class<T> clazz) {
        final SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        return parse(mapper, jsonFile, clazz);
    }

    public final <T> T readAsObject(final File jsonFile, final Class<T> clazz) {
        return parse(new ObjectMapper(), jsonFile, clazz);
    }

    private <T> T parse(final ObjectMapper mapper, final File jsonFile, final Class<T> clazz) {
        try {
            return mapper.readValue(jsonFile, clazz);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(MSG_ERRO, e);
        }
    }

    public final String readAsString(final Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(MSG_ERRO, e);
        }
    }

    /**
     * Data-hora no formato "yyyy-MM-dd hh:mm:ss:SSS"
     * 
     * @param o
     * @return
     */
    public final String writeWithDefaultDateFormat(final Object o) {
        try {
            final SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(df);

            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException("Erro ao serializar objeto", e);
        }
    }

    /**
     * Data-hora no formato "yyyy-MM-dd hh:mm:ss:SSS"
     * 
     * @param o
     * @return
     */
    public final String writeWithDefaultDateFormatIgnoringNullFields(final Object o) {
        try {
            final SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setDateFormat(df);

            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException("Erro ao serializar objeto", e);
        }
    }
    
    /**
     * Cria uma lista de Objects a partir de uma string Json
     * 
     * @param json
     * @param clazz
     * @return List object
     */
    public final <T> List<Object> getListObjectByJsonString(String json, final TypeReference<T> clazz) {
        List<Object> list = new ArrayList<>();
        try {
        	list.addAll(new ObjectMapper().readValue(json, clazz));
        } catch (Exception e) {
        	logger.error(e.getMessage());
        	throw new IllegalArgumentException("Erro ao serializar objeto", e);
        }
        return list;
    }

}