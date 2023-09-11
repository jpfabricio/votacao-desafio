package br.com.brq.votacao.controller;

import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.model.Voto;
import br.com.brq.votacao.model.enumerated.TipoVoto;
import br.com.brq.votacao.repository.VotoRepository;
import br.com.brq.votacao.service.VotoService;
import br.com.brq.votacao.service.filter.VotoFilter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Configuration
@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private VotoService service;

    @Autowired
    private VotoRepository repository;

    @Autowired
    private VotoFilter filter;

    @ResponseBody
    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cria um novo voto em uma pauta")
    public Voto novo(@RequestParam(name = "associado_id", required = true) @NotBlank(message = "ID de associado é obrigatório!") final String associadoId,
                     @RequestParam(name = "voto", required = true) final TipoVoto voto,
                     @RequestParam(name = "pauta_id", required = true) @Valid @NotBlank(message = "ID de pauta é obrigatório!") final String pautaId) throws BadRequest {
        filter.validaNovoVoto(pautaId, associadoId);
        return service.novo(associadoId, voto, pautaId);
    }

}