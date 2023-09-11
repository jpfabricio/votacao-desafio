package br.com.brq.votacao.service;

import br.com.brq.votacao.model.Voto;
import br.com.brq.votacao.model.enumerated.TipoVoto;
import br.com.brq.votacao.repository.service.AssociadoRepositoryService;
import br.com.brq.votacao.repository.service.PautaRepositoryService;
import br.com.brq.votacao.repository.service.VotoRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private VotoRepositoryService votoRepositoryService;

    @Autowired
    private AssociadoRepositoryService associadoRepositoryService;

    @Autowired
    private PautaRepositoryService pautaRepositoryService;

    public Voto novo(String associadoId, TipoVoto tipoVoto, String pautaId) {
        Voto voto = new Voto(tipoVoto, associadoRepositoryService.buscaPorId(associadoId).getId(), pautaRepositoryService.buscaPorId(pautaId).getId());
        pautaService.contabilizaVoto(pautaId, voto);
        votoRepositoryService.salva(voto);
        return voto;
    }
}