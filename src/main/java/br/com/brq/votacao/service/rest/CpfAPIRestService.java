package br.com.brq.votacao.service.rest;

import br.com.brq.votacao.exception.BadGateway;
import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.repository.rest.CpfAPIRestRepository;
import br.com.brq.votacao.util.CodigosErros;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class CpfAPIRestService {

    private static Logger logger = LoggerFactory.getLogger(CpfAPIRestService.class);

    @Autowired
    private CpfAPIRestRepository restRepository;

    public void validaCpf(String cpf) throws BadRequest, BadGateway {
        try {
            Map<String, String> map = Map.of("acao", "validar_cpf", "txt_cpf", cpf);
            String  response = restRepository.validaCpf(map);
            validaAptidaoParaVoto(response);
        }catch (FeignException e){
            logger.error(CodigosErros.getMensagem(CodigosErros.ERRO_NA_CHAMADA_EXTERNA_VALIDA_CPF));
            throw new BadGateway(CodigosErros.getMensagem(CodigosErros.ERRO_NA_CHAMADA_EXTERNA_VALIDA_CPF), CodigosErros.getCodigo(CodigosErros.ERRO_NA_CHAMADA_EXTERNA_VALIDA_CPF));
        }
    }

    private static void validaAptidaoParaVoto(String response) throws BadRequest {
        if (response.contains("Falso")){
            logger.error(CodigosErros.getMensagem(CodigosErros.CPF_INVALIDO));
            throw new BadRequest(CodigosErros.getMensagem(CodigosErros.CPF_INVALIDO), CodigosErros.getCodigo(CodigosErros.CPF_INVALIDO));
        }
    }
}