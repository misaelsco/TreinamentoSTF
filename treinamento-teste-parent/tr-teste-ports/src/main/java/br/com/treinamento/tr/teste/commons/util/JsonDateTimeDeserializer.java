package br.com.treinamento.tr.teste.commons.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateTimeDeserializer extends JsonDeserializer<LocalDate> {

    private static final Logger LOG = LoggerFactory.getLogger(JsonDateTimeDeserializer.class);

    @Override
    public LocalDate deserialize(JsonParser dataParaDeserializar, DeserializationContext context) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDate.parse(dataParaDeserializar.getText(), formatter);
        } catch (IOException e) {
            LOG.error("NÃo foi possivel Deserializar " + dataParaDeserializar, e);
        }

        return null;
    }
}
