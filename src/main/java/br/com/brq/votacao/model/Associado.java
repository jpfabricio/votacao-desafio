package br.com.brq.votacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document("associado")
public class Associado {

    @Id
    private String id = UUID.randomUUID().toString();

    @Indexed(unique = true)
    private String cpf;

    private List<Voto> votos = new ArrayList<>();

    public Associado(String cpf) {
        this.cpf = cpf;
    }
}