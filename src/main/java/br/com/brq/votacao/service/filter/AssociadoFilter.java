package br.com.brq.votacao.service.filter;

import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.repository.AssociadoRepository;
import br.com.brq.votacao.util.CodigosErros;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssociadoFilter {

    @Autowired
    private AssociadoRepository repository;

    private static Logger logger = LoggerFactory.getLogger(AssociadoFilter.class);

    public void validaNovoAssociado(final String cpf) throws BadRequest {
        if(repository.findByCpf(cpf).isPresent()){
            logger.error(CodigosErros.getMensagem(CodigosErros.CPF_JA_UTILIZADO));
            throw new BadRequest(CodigosErros.getMensagem(CodigosErros.CPF_JA_UTILIZADO), CodigosErros.getCodigo(CodigosErros.CPF_JA_UTILIZADO));
        }
    }
}