package br.com.brq.votacao.service;

import br.com.brq.votacao.model.Associado;
import br.com.brq.votacao.model.Pauta;
import br.com.brq.votacao.model.Voto;
import br.com.brq.votacao.model.enumerated.TipoVoto;
import br.com.brq.votacao.repository.service.PautaRepositoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {

    private Pauta pauta;

    @Mock
    private PautaRepositoryService repository;

    @InjectMocks
    private PautaService pautaUseCase;

    @Before
    public void init() {
        this.pauta = new Pauta("Pauta Teste");
    }

    @Test
    public void validaNovaPauta(){
        when(repository.salva(any(Pauta.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        Pauta pauta = pautaUseCase.nova("Pauta Teste");
        assertNotNull(pauta);
        assertEquals(pauta.getNome(), "Pauta Teste");
    }

    @Test
    public void validaAberturaSessao(){
        when(repository.buscaPorId(anyString())).thenReturn(this.pauta);
        when(repository.salva(any(Pauta.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        this.pauta = pautaUseCase.abreSessao(this.pauta.getId(), LocalDateTime.now().plusDays(10));
        assertNotNull(this.pauta.getDataExpiracaoSessao());
        assertTrue(this.pauta.isSessaoDisponivel());
    }

    @Test
    public void validaContabilizaVoto(){
        when(repository.buscaPorId(anyString())).thenReturn(this.pauta);
        when(repository.salva(any(Pauta.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        this.pauta = pautaUseCase.contabilizaVoto(this.pauta.getId(), new Voto(TipoVoto.SIM, new Associado("").getId(), pauta.getId()));
        assertFalse(this.pauta.getVotos().isEmpty());
    }
}
