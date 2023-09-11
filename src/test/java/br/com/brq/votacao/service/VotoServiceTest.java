package br.com.brq.votacao.service;

import br.com.brq.votacao.model.Associado;
import br.com.brq.votacao.model.Pauta;
import br.com.brq.votacao.model.Voto;
import br.com.brq.votacao.model.enumerated.TipoVoto;
import br.com.brq.votacao.repository.service.AssociadoRepositoryService;
import br.com.brq.votacao.repository.service.PautaRepositoryService;
import br.com.brq.votacao.repository.service.VotoRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VotoServiceTest {

    @Mock
    private AssociadoRepositoryService associadoRepository;

    @Mock
    private PautaRepositoryService pautaRepository;

    @Mock
    private VotoRepositoryService votoRepository;

    @Mock
    private PautaService pautaService;

    @InjectMocks
    private VotoService votoService;

    @Test
    public void validaNovoVoto(){
        Pauta pauta = new Pauta("Pauta Teste");
        when(pautaService.contabilizaVoto(anyString(), any(Voto.class))).thenReturn(pauta);
        when(associadoRepository.buscaPorId(anyString())).thenReturn(new Associado("0000"));
        when(pautaRepository.buscaPorId(anyString())).thenReturn(pauta);

        Voto voto = votoService.novo("123456", TipoVoto.SIM, "654321");
        assertNotNull(voto);
        assertEquals(voto.getTipoVoto(), TipoVoto.SIM);
        assertEquals(voto.getAssociado().getCpf(), "0000");
        assertEquals(voto.getPauta().getNome(), "Pauta Teste");
    }
}
