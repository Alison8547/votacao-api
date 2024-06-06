package com.br.voting.mapper;

import com.br.voting.builders.PautaBuilder;
import com.br.voting.domain.Pauta;
import com.br.voting.dto.request.PautaRequest;
import com.br.voting.dto.response.PautaResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PautaMapperTest {

    @InjectMocks
    private PautaMapper pautaMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testMustToPautaWithSuccess() {
        PautaRequest pautaRequest = new PautaRequest("Pauta");

        when(modelMapper.map(pautaRequest, Pauta.class)).thenReturn(PautaBuilder.newPautaEntity());

        Pauta pauta = pautaMapper.toPauta(pautaRequest);

        assertNotNull(pauta);
        assertEquals("pauta 2.4", pauta.getPauta());
    }

    @Test
    public void testMustToPautaResponseWithSuccess() {
        Pauta pauta = new Pauta(1, "Pauta", LocalDateTime.now());
        PautaRequest pautaRequest = new PautaRequest("Pauta");

        when(modelMapper.map(pauta, PautaResponse.class)).thenReturn(PautaBuilder.newPautaResponse());

        PautaResponse pautaResponse = pautaMapper.toPautaResponse(pauta);

        assertNotNull(pautaResponse);
        assertEquals("pauta 2.4", pautaResponse.getPauta());
        assertEquals(1, pautaResponse.getIdPauta());
    }
}
