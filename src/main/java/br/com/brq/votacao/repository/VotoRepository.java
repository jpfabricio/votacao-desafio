package br.com.brq.votacao.repository;

import br.com.brq.votacao.model.Voto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends MongoRepository<Voto, Long> {

    Voto save(final Voto Voto);

    Optional<Voto> findById(final String id);

    Optional<Voto> findByAssociadoIdAndPautaId(String associadoId, String pautaId);
}