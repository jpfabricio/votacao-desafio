package br.com.brq.votacao.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonIgnoreProperties({ "stackTrace", "suppressed", "localizedMessage" })
public class BadRequest extends Exception{

	@Getter
	private String message;

	@Getter
	private int code;

	public BadRequest(String message, int code) {
		this.message = message;
		this.code = code;
	}
	
}
