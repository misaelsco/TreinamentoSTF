package br.com.treinamento.tr.core.commons.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final Logger LOG = LoggerFactory.getLogger(JsonDateTimeDeserializer.class);

    @Override
    public LocalDateTime deserialize(JsonParser dataParaDeserializar, DeserializationContext context) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        try {
            return LocalDateTime.parse(dataParaDeserializar.getText(), formatter);
        } catch (IOException e) {
            LOG.error("NÃo foi possivel Deserializar " + dataParaDeserializar, e);
        }

        return null;
    }
}
