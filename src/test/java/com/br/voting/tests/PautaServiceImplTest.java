package com.br.voting.tests;


import com.br.voting.builders.PautaBuilder;
import com.br.voting.domain.Pauta;
import com.br.voting.dto.response.PautaResponse;
import com.br.voting.exception.BusinessException;
import com.br.voting.mapper.PautaMapper;
import com.br.voting.repository.PautaRepository;
import com.br.voting.service.PautaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceImplTest {

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;


    @Test
    public void testMustCreatePautaWithSuccess() {
        //(SETUP)
        when(pautaRepository.save(any())).thenReturn(PautaBuilder.newPautaEntity());
        when(pautaMapper.toPauta(any())).thenReturn(PautaBuilder.newPautaEntity());
        when(pautaMapper.toPautaResponse(any())).thenReturn(PautaBuilder.newPautaResponse());

        //(ACT)
        PautaResponse pautaSave = pautaService.createPauta(PautaBuilder.newPautaRequest());

        //(ASSERT)
        assertNotNull(pautaSave);
        assertNotNull(pautaSave.getIdPauta());
        assertEquals(pautaSave.getPauta(), PautaBuilder.newPautaRequest().getPauta());

    }

    @Test
    public void testMustFindByIdPautaWithSuccess() {
        //(SETUP)
        when(pautaRepository.findById(anyInt())).thenReturn(Optional.of(PautaBuilder.newPautaEntity()));

        //(ACT)
        Pauta pautaFind = pautaService.findPauta(PautaBuilder.newPautaEntity().getIdPauta());

        //(ASSERT)
        assertNotNull(pautaFind);


    }

    @Test(expected = BusinessException.class)
    public void testMustFindByIdWithError() {
        //(SETUP)
        Integer search = 12;
        when(pautaRepository.findById(anyInt())).thenReturn(Optional.empty());

        //(ACT)
        Pauta pautaFind = pautaService.findPauta(search);

        //(ASSERT)
        assertNull(pautaFind);
    }
}