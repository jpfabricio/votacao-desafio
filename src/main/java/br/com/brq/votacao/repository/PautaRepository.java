package br.com.brq.votacao.repository;

import br.com.brq.votacao.model.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, String> {
    Pauta save(final Pauta pauta);

    Optional<Pauta> findById(final String id);

    Optional<Pauta> findByNome(final String nome);
}