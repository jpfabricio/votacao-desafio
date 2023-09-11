package br.com.brq.votacao.controller;

import br.com.brq.votacao.exception.BadGateway;
import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.model.Associado;
import br.com.brq.votacao.service.AssociadoService;
import br.com.brq.votacao.service.filter.AssociadoFilter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Configuration
@RestController
@RequestMapping("/associado")
public class AssociadoController {

    @Autowired
    private AssociadoService service;

    @Autowired
    private AssociadoFilter filter;

    @ResponseBody
    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cria um novo associado")
    public Associado novo(@RequestParam(name = "cpf", required = true) @NotBlank(message = "CPF é obrigatório!") final String cpf) throws BadRequest, BadGateway {
        filter.validaNovoAssociado(cpf);
        return service.novo(cpf);
    }
}