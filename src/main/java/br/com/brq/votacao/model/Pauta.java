package br.com.brq.votacao.model;

import br.com.brq.votacao.model.enumerated.TipoVoto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "pauta")
@NoArgsConstructor
public class Pauta {

    @Id
    private String id = UUID.randomUUID().toString();

    @Indexed(unique = true)
    private String nome;

    private List<Voto> votos = new ArrayList<>();

    @Field(name = "sessao_disponivel")
    private boolean sessaoDisponivel = false;

    @Field(name = "data_expiracao_sessao")
    private LocalDateTime dataExpiracaoSessao;

    private ResultadoPauta resultado;

    public Pauta(String nome) {
        this.nome = nome;
    }

    public void abreSessao(LocalDateTime dataExpiracaoSessao) {
        if (dataExpiracaoSessao == null)
            dataExpiracaoSessao = LocalDateTime.now().plusMinutes(1);
        this.dataExpiracaoSessao = dataExpiracaoSessao;
        this.sessaoDisponivel = true;
    }

    public void finalizaSessao() {
        this.sessaoDisponivel = false;
        this.contabilizaResultado();
    }

    public void contabilizaResultado() {
        long votosSim = this.votos.stream().filter(v -> v.getTipoVoto() == TipoVoto.SIM).count();
        long votosNao = this.votos.stream().filter(v -> v.getTipoVoto() == TipoVoto.NAO).count();

        this.resultado = new ResultadoPauta(this.id, votosSim + votosNao, votosSim, votosNao);
    }

    public void adicionaVoto(Voto voto) {
        this.votos.add(voto);
    }
}