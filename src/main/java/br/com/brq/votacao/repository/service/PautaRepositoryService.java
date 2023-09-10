package br.com.brq.votacao.repository.service;

import br.com.brq.votacao.repository.PautaRepository;
import br.com.brq.votacao.model.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PautaRepositoryService {

    @Autowired
    private PautaRepository repository;

    public Pauta salva(final Pauta pauta){
        return repository.save(pauta);
    }

    public Pauta buscaPorId(final String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe uma pauta com o ID informado!"));
    }

    public Pauta buscaPorNome(final String nome) {
        return repository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe uma pauta com o nome informado!"));
    }
}