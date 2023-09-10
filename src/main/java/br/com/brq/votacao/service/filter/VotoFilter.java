package br.com.brq.votacao.service.filter;

import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.repository.VotoRepository;
import br.com.brq.votacao.repository.service.PautaRepositoryService;
import br.com.brq.votacao.util.CodigosErros;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VotoFilter {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private PautaRepositoryService pautaRepositoryService;

    private static Logger logger = LoggerFactory.getLogger(VotoFilter.class);

    private final String SESSAO_NAO_DISPONIVEL = "Sessão de votação para esta pauta não está disponivel!";

    private final String ASSOCIADO_JA_VOTOU = "Associado já votou nessa pauta!";

    public void validaNovoVoto(final String pautaId, final String associadoId) throws BadRequest {
        if (!pautaRepositoryService.buscaPorId(pautaId).isSessaoDisponivel()){
            logger.info(CodigosErros.getMensagem(CodigosErros.SESSAO_NAO_DISPONIVEL));
            throw new BadRequest(CodigosErros.getMensagem(CodigosErros.SESSAO_NAO_DISPONIVEL), CodigosErros.getCodigo(CodigosErros.SESSAO_NAO_DISPONIVEL));
        }

        if (votoRepository.findByAssociadoIdAndPautaId(associadoId, pautaId).isPresent()){
            logger.info(CodigosErros.getMensagem(CodigosErros.ASSOCIADO_JA_VOTOU));
            throw new BadRequest(CodigosErros.getMensagem(CodigosErros.ASSOCIADO_JA_VOTOU), CodigosErros.getCodigo(CodigosErros.ASSOCIADO_JA_VOTOU));
        }
    }
}