package br.com.brq.votacao.service;

import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.model.Pauta;
import br.com.brq.votacao.model.Voto;
import br.com.brq.votacao.repository.service.PautaRepositoryService;
import br.com.brq.votacao.util.CodigosErros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class PautaService {

    @Autowired
    private PautaRepositoryService repositoryService;

    public Pauta nova(String nome) {
        return repositoryService.salva(new Pauta(nome));
    }

    public Pauta abreSessao(String id, LocalDateTime dataExpiracao) {
        Pauta pauta = repositoryService.buscaPorId(id);
        pauta.abreSessao(dataExpiracao);
        agendaFimSessao(pauta);
        return repositoryService.salva(pauta);
    }

    public Pauta contabilizaVoto(String id, Voto voto) {
        Pauta pauta = repositoryService.buscaPorId(id);
        pauta.adicionaVoto(voto);
        return repositoryService.salva(pauta);
    }

    private void agendaFimSessao(Pauta pauta) {
        new Timer().schedule(finalizaSessao(pauta.getId()), Date
                .from(pauta.getDataExpiracaoSessao()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()));
    }

    private TimerTask finalizaSessao(String pautaId) {
        return new TimerTask() {
            @Override
            public void run() {
                Pauta pauta = repositoryService.buscaPorId(pautaId);
                pauta.finalizaSessao();
                repositoryService.salva(pauta);
            }
        };
    }

    public Pauta resultado(String id) throws BadRequest {
        Pauta pauta = repositoryService.buscaPorId(id);
        if (pauta.isSessaoDisponivel()){
            throw new BadRequest(CodigosErros.getMensagem(CodigosErros.VOTACAO_AINDA_ATIVA).concat("a sessao acaba em: ")
                    .concat(pauta.getDataExpiracaoSessao().toString()),
                    CodigosErros.getCodigo(CodigosErros.VOTACAO_AINDA_ATIVA));
        }
        return pauta;
    }
}