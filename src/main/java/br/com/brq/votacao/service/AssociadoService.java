package br.com.brq.votacao.service;

import br.com.brq.votacao.model.Associado;
import br.com.brq.votacao.repository.service.AssociadoRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepositoryService repositoryService;

    public Associado novo(String cpf) {
        return repositoryService.salva(new Associado(cpf));
    }
}