package br.com.brq.votacao.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties({ "stackTrace", "suppressed", "localizedMessage" })
public class BadGateway extends Exception{

	@Getter
	private String message;

	@Getter
	private int code;

	public BadGateway(String message, int code) {
		this.message = message;
		this.code = code;
	}
	
}
