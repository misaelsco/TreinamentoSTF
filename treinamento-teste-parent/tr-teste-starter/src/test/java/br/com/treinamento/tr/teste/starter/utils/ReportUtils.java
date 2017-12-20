package br.com.treinamento.tr.teste.starter.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe utilitária para formatação de textos exibidos no relatório dos testes.
 */
@Component
public class ReportUtils {


	@Autowired
	private JacksonJsonHelper jsonHelper;

	private ReportUtils() {
	}

	static StringBuilder formatStepName(String step) {
		StringBuilder builder = new StringBuilder();

		builder.append("<b>");
		builder.append(step);
		builder.append("</b><br><br>");
		return builder;
	}

	public void report(String step, StringBuilder message) {
		StringBuilder builder = formatStepName(step);
		builder.append(message);
		ExtentCucumberFormatter.setStepDetailsMessage(builder.toString());
	}

	public void report(String step, Object obj) {
		StringBuilder builder = formatStepName(step);
		builder.append("<b> " + obj.getClass().getSimpleName() + ": </b>");
		builder.append(jsonHelper.writeWithDefaultDateFormatIgnoringNullFields(obj));
		ExtentCucumberFormatter.setStepDetailsMessage(builder.toString());
	}

	public <T> void report(String step, List<T> list) {
		StringBuilder builder = formatStepName(step);
		list.forEach(l -> {
			builder.append("<b> " + list.get(0).getClass().getSimpleName() + ": </b>");
			builder.append(jsonHelper.writeWithDefaultDateFormatIgnoringNullFields(l));
			builder.append("<br><br>");
		});
		ExtentCucumberFormatter.setStepDetailsMessage(builder.toString());
	}
}
