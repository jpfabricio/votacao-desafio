package br.com.brq.votacao.service.filter;

import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.repository.PautaRepository;
import br.com.brq.votacao.util.CodigosErros;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
public class PautaFilter {

    @Autowired
    private PautaRepository repository;

    private static Logger logger = LoggerFactory.getLogger(PautaFilter.class);

    public void validaNovaPauta (final String nome) throws BadRequest {
        if (repository.findByNome(nome).isPresent()){
            logger.info(CodigosErros.getMensagem(CodigosErros.JA_EXISTE_PAUTA_COM_ESSE_NOME));
            throw new BadRequest(CodigosErros.getMensagem(CodigosErros.JA_EXISTE_PAUTA_COM_ESSE_NOME), CodigosErros.getCodigo(CodigosErros.JA_EXISTE_PAUTA_COM_ESSE_NOME));
        }
    }

    public void validaAberturaSessao (final String pautaId, final LocalDateTime dataExpiracao) throws BadRequest {
        if (dataExpiracao != null && dataExpiracao.isBefore(LocalDateTime.now())){
            logger.error(CodigosErros.getMensagem(CodigosErros.DATA_EXPIRACAO_INVALIDA));
            throw new BadRequest(CodigosErros.getMensagem(CodigosErros.DATA_EXPIRACAO_INVALIDA), CodigosErros.getCodigo(CodigosErros.DATA_EXPIRACAO_INVALIDA));
        }
    }
}