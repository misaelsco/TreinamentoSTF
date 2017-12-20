package br.com.treinamento.tr.core.commons.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    // lib shared
    private static final Logger LOG = LoggerFactory.getLogger(JsonDateTimeSerializer.class);

    @Override
    public void serialize(LocalDateTime dataParaSerializar, JsonGenerator dataSerializada,
            SerializerProvider provider) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.parse(dataParaSerializar.toString());

        String formatLocalDateTime = formatter.format(localDateTime);

        try {
            dataSerializada.writeString(formatLocalDateTime);
        } catch (IOException e) {
            LOG.error("NÃo foi possivel Serializar " + dataParaSerializar, e);
        }
    }

}
