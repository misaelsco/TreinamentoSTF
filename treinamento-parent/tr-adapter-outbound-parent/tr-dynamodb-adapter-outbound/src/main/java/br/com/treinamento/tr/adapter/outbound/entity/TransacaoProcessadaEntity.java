package br.com.treinamento.tr.adapter.outbound.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.treinamento.tr.core.commons.dto.TransacaoProcessadaDTO;
import br.com.treinamento.tr.core.commons.enumeration.TipoTransacaoEnum;
import br.com.treinamento.tr.core.commons.util.JacksonJsonHelper;
import br.com.treinamento.tr.core.commons.util.JsonDateTimeDeserializer;
import br.com.treinamento.tr.core.commons.util.JsonDateTimeSerializer;

//@DynamoDBTable(tableName = "REPLACED_BY_VALUE_IN_PROPERTIES_FILE")
public class TransacaoProcessadaEntity{
	
	

	private Integer idConta;

	private Double valor;

	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private LocalDate dataEnvio;

	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private LocalDate dataProcessada;
	
	private TipoTransacaoEnum tipo;
	
	
	public TransacaoProcessadaEntity() {
	}

	public TransacaoProcessadaEntity(TransacaoProcessadaDTO transacao) {
	}

	
	public static void main(String[] args) {
		TransacaoProcessadaEntity tr = new TransacaoProcessadaEntity();
		
		JacksonJsonHelper js = new JacksonJsonHelper();
		
		System.out.println(js.writeWithDefaultDateFormatIgnoringNullFields(tr));
	}
	
}