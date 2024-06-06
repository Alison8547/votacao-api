package com.br.voting.mapper;

import com.br.voting.builders.VotingSessionBuilder;
import com.br.voting.domain.VotingSession;
import com.br.voting.dto.request.VotingSessionRequest;
import com.br.voting.dto.response.VotingSessionResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VotingSessionMapperTest {

    @InjectMocks
    private VotingSessionMapper votingSessionMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testMustToVotingSessionWithSuccess() {
        VotingSessionRequest votingSessionRequest = VotingSessionBuilder.newVotingSessionRequest();

        when(modelMapper.map(votingSessionRequest, VotingSession.class)).thenReturn(VotingSessionBuilder.newVotingSessionEntity());

        VotingSession votingSession = votingSessionMapper.toVotingSession(votingSessionRequest);

        assertNotNull(votingSession);
        assertEquals(2, votingSession.getIdVotingSession());

    }

    @Test
    public void testMustToVotingSessionResponseWithSuccess() {
        VotingSession votingSession = VotingSessionBuilder.newVotingSessionEntity();

        when(modelMapper.map(votingSession, VotingSessionResponse.class)).thenReturn(VotingSessionBuilder.newVotingSessionResponse());

        VotingSessionResponse votingSessionResponse = votingSessionMapper.toVotingSessionResponse(votingSession);

        assertNotNull(votingSessionResponse);
        assertEquals(2, votingSessionResponse.getIdVotingSession());
        assertEquals(1, votingSessionResponse.getPautaVoting().getIdPauta());
        assertEquals("pauta 2.4", votingSessionResponse.getPautaVoting().getPauta());

    }
}
