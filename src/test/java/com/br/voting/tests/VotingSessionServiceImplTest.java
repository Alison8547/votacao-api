package com.br.voting.tests;


import com.br.voting.builders.PautaBuilder;
import com.br.voting.builders.VotingSessionBuilder;
import com.br.voting.domain.VotingSession;
import com.br.voting.dto.response.VotingSessionResponse;
import com.br.voting.exception.BusinessException;
import com.br.voting.mapper.VotingSessionMapper;
import com.br.voting.repository.VotingSessionRepository;
import com.br.voting.service.PautaServiceImpl;
import com.br.voting.service.VotingSessionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VotingSessionServiceImplTest {

    @InjectMocks
    private VotingSessionServiceImpl votingSessionService;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Mock
    private PautaServiceImpl pautaService;

    @Mock
    private VotingSessionMapper votingSessionMapper;


    @Test
    public void testMustCreateVotingSessionWithSuccess() {
        //(SETUP)
        when(pautaService.findPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntityNoSession());
        when(votingSessionMapper.toVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(votingSessionRepository.save(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(votingSessionMapper.toVotingSessionResponse(any())).thenReturn(VotingSessionBuilder.newVotingSessionResponse());

        //(ACT)
        VotingSessionResponse votingSessionOpen = votingSessionService.openSession(7, VotingSessionBuilder.newVotingSessionRequest());

        //(ASSERT)
        assertNotNull(votingSessionOpen);

    }

    @Test(expected = BusinessException.class)
    public void testMustCreateVotingSessionAlreadyExistsWithError() {
        //(SETUP)
        when(pautaService.findPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntity());
        when(votingSessionRepository.findByPauta(any())).thenReturn(Optional.of(VotingSessionBuilder.newVotingSessionEntity()));

        //(ACT)
        VotingSessionResponse votingSessionOpen = votingSessionService.openSession(1, VotingSessionBuilder.newVotingSessionRequest());

        //(ASSERT)
        assertNull(votingSessionOpen);

    }

    @Test
    public void testMustCreateVotingSessionWithDefaultTime() {
        //(SETUP)
        when(pautaService.findPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntityNoSession());
        when(votingSessionMapper.toVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntityDefaultTime());
        when(votingSessionRepository.save(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(votingSessionMapper.toVotingSessionResponse(any())).thenReturn(VotingSessionBuilder.newVotingSessionResponse());

        //(ACT)
        VotingSessionResponse votingSessionOpen = votingSessionService.openSession(7, VotingSessionBuilder.newVotingSessionEntityDefaultTimeRequest());

        //(ASSERT)
        assertNotNull(votingSessionOpen);

    }

    @Test(expected = BusinessException.class)
    public void testMustCreateVotingSessionBeforeTimeDataOpenError() {
        //(SETUP)
        when(pautaService.findPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntityNoSession());
        when(votingSessionMapper.toVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntityBeforeDataOpen());

        //(ACT)
        VotingSessionResponse votingSessionOpen = votingSessionService.openSession(7, VotingSessionBuilder.newVotingSessionRequest());

        //(ASSERT)
        assertNotNull(votingSessionOpen);
    }

    @Test(expected = BusinessException.class)
    public void testMustGetVotingSessionError() {
        //(SETUP)

        //(ACT)
        VotingSession votingSession = votingSessionService.getVotingSession(PautaBuilder.newPautaEntity());

        //(ASSERT)
        assertNull(votingSession);
    }


}

