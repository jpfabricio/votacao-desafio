package br.com.brq.votacao.service;

import br.com.brq.votacao.exception.BadGateway;
import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.model.Associado;
import br.com.brq.votacao.repository.service.AssociadoRepositoryService;
import br.com.brq.votacao.service.rest.CpfAPIRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepositoryService repositoryService;

    @Autowired
    private CpfAPIRestService cpfAPIRestService;

    public Associado novo(final String cpf) throws BadRequest, BadGateway {
        verificaCpf(cpf);
        return repositoryService.salva(new Associado(cpf));
    }

    public void verificaCpf(final String cpf) throws BadRequest, BadGateway {
        cpfAPIRestService.validaCpf(cpf);
    }
}