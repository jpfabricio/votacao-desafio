package br.com.brq.votacao.repository;

import br.com.brq.votacao.model.Associado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends MongoRepository<Associado, Long> {

    Associado save(final Associado Associado);

    Optional<Associado> findById(final String id);

    Optional<Associado> findByCpf(final String cpf);
}