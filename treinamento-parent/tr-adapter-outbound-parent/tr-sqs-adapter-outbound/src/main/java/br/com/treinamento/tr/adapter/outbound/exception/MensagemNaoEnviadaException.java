package br.com.treinamento.tr.adapter.outbound.exception;

public class MensagemNaoEnviadaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
    public MensagemNaoEnviadaException(String message) {
        super(message);
    }
	
}


