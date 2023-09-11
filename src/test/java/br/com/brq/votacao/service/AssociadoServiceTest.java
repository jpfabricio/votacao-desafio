package br.com.brq.votacao.service;

import br.com.brq.votacao.exception.BadGateway;
import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.model.Associado;
import br.com.brq.votacao.repository.service.AssociadoRepositoryService;
import br.com.brq.votacao.service.rest.CpfAPIRestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AssociadoServiceTest {

    @Mock
    private AssociadoRepositoryService repository;

    @Mock
    private CpfAPIRestService cpfAPIService;

    @InjectMocks
    private AssociadoService associadoService;

    @Test
    public void validaNovoAssociado() throws BadRequest, BadGateway {
        doNothing().when(cpfAPIService).validaCpf(anyString());
        when(repository.salva(any(Associado.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        Associado associado = associadoService.novo("123456");
        assertNotNull(associado);
        assertEquals(associado.getCpf(), "123456");
    }
}
