package br.com.brq.votacao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document("resultado_pauta")
public class ResultadoPauta {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field(name = "pauta_id")
    private String pautaId;

    @Field(name = "votos_totais")
    private long totalVotos;

    @Field(name = "votos_totais_sim")
    private long totalVotosSim;

    @Field(name = "votos_totais_nao")
    private long totalVotosNao;

    public ResultadoPauta(String pautaId, long totalVotos, long totalVotosSim, long totalVotosNao) {
        this.pautaId = pautaId;
        this.totalVotos = totalVotos;
        this.totalVotosSim = totalVotosSim;
        this.totalVotosNao = totalVotosNao;
    }
}