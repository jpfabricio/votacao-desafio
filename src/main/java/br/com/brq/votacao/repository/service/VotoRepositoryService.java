package br.com.brq.votacao.repository.service;

import br.com.brq.votacao.repository.VotoRepository;
import br.com.brq.votacao.model.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VotoRepositoryService {

    @Autowired
    private VotoRepository repository;

    public Voto salva(Voto voto){
        return repository.save(voto);
    }

    public Voto buscaPorId(final String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe uma voto com o ID informado!"));
    }

}