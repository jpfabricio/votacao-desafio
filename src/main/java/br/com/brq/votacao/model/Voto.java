package br.com.brq.votacao.model;

import br.com.brq.votacao.model.enumerated.TipoVoto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document("voto")
public class Voto {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field(name = "tipo_voto")
    private TipoVoto tipoVoto;

    private String associadoId;

    private String pautaId;


    public Voto(TipoVoto tipoVoto, String associadoId, String pautaId) {
        this.tipoVoto = tipoVoto;
        this.associadoId = associadoId;
        this.pautaId = pautaId;
    }
}