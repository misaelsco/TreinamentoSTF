package br.com.treinamento.tr.teste.commons.util;

import java.io.IOException;
import java.time.LocalDate;
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

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(dataParaSerializar.toString());

        String formatLocalDateTime = formatter.format(localDate);

        try {
            dataSerializada.writeString(formatLocalDateTime);
        } catch (IOException e) {
            LOG.error("NÃo foi possivel Serializar " + dataParaSerializar, e);
        }
    }

}
