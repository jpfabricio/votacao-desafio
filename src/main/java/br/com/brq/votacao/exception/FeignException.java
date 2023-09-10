package br.com.brq.votacao.exception;

import br.com.brq.votacao.controller.dto.ErroDTO;
import lombok.Getter;

public class FeignException extends Exception{

	@Getter
	private ErroDTO erro;

	public FeignException(ErroDTO erro, int code) {
		this.erro = erro;
		erro.setCode(code);
	}
	
}
