package br.com.brq.votacao.repository.service;

import br.com.brq.votacao.model.Associado;
import br.com.brq.votacao.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AssociadoRepositoryService {

    @Autowired
    private AssociadoRepository repository;

    public Associado salva(Associado associado){
        return repository.save(associado);
    }

    public Associado buscaPorId(final String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe uma associado com o ID informado!"));
    }

    public Associado buscaPorCpf(final String cpf) {
        return repository.findByCpf(cpf).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe uma associado com o CPF informado!"));
    }
}