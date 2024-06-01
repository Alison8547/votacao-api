package com.br.voting.tests;

import com.br.voting.builders.PautaBuilder;
import com.br.voting.builders.VoteBuilder;
import com.br.voting.builders.VotingSessionBuilder;
import com.br.voting.domain.enums.Status;
import com.br.voting.dto.response.ResultVotingResponse;
import com.br.voting.dto.response.ValidCpfResponse;
import com.br.voting.dto.response.ValidResponse;
import com.br.voting.dto.response.VoteResponse;
import com.br.voting.exception.BusinessException;
import com.br.voting.infrastructure.client.invertexto.InverTextoApiClient;
import com.br.voting.mapper.VoteMapper;
import com.br.voting.repository.VoteRepository;
import com.br.voting.service.PautaServiceImpl;
import com.br.voting.service.VoteServiceImpl;
import com.br.voting.service.VotingSessionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceImplTest {

    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VotingSessionServiceImpl votingSessionService;

    @Mock
    private PautaServiceImpl pautaService;

    @Mock
    private VoteMapper voteMapper;

    @Mock
    private InverTextoApiClient inverTextoApiClient;


    @Test
    public void testMustVoteWithSuccess() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(voteMapper.toVote(any())).thenReturn(VoteBuilder.newVoteEntity());
        when(voteRepository.save(any())).thenReturn(VoteBuilder.newVoteEntity());
        when(voteRepository.existsByVotingSessionAndCpfAssociate(any(), any())).thenReturn(false);

        //(ACT)

        voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteNewCpfRequest());

        //(ASSERT)
        assertEquals("501.980.140-23", VoteBuilder.newVoteNewCpfRequest().getCpfAssociate());
        assertEquals("Sim", VoteBuilder.newVoteYesRequest().getMessageVote());

    }

    @Test(expected = BusinessException.class)
    public void testMustVoteOffSessionWithError() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntityBefore());

        //(ACT)

        VoteResponse vote = voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteYesRequest());

        //(ASSERT)
        assertNull(vote);

    }

    @Test
    public void testMustVoteNoWithSuccess() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(voteMapper.toVote(any())).thenReturn(VoteBuilder.newVoteEntity());
        when(voteRepository.save(any())).thenReturn(VoteBuilder.newVoteEntity());

        //(ACT)

        voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteNoRequest());

        //(ASSERT)
        assertEquals("943.657.200-07", VoteBuilder.newVoteNoRequest().getCpfAssociate());
        assertEquals("Não", VoteBuilder.newVoteNoRequest().getMessageVote());

    }

    @Test(expected = BusinessException.class)
    public void testMustVoteValidMessageWithSuccess() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(voteMapper.toVote(any())).thenReturn(VoteBuilder.newVoteEntity());

        //(ACT)

        VoteResponse vote = voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteValidMessageRequest());

        //(ASSERT)
        assertNull(vote);

    }

    @Test(expected = BusinessException.class)
    public void testMustVoteCpfAlreadyExistsWithError() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(voteMapper.toVote(any())).thenReturn(VoteBuilder.newVoteEntity());
        when(voteRepository.existsByVotingSessionAndCpfAssociate(any(), any())).thenReturn(true);

        //(ACT)

        VoteResponse vote = voteService.vote(PautaBuilder.newPautaEntity().getIdPauta(), VoteBuilder.newVoteYesRequest());

        //(ASSERT)
        assertNull(vote);
    }

    @Test
    public void testMustVoteResultSuccess() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntityBefore());
        when(pautaService.findPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntity());

        //(ACT)
        ResultVotingResponse result = voteService.resultVoting(PautaBuilder.newPautaEntity().getIdPauta());


        //(ASSERT)
        assertNotNull(result);
        assertEquals(PautaBuilder.newPautaEntity().getPauta(), result.getPauta());
        assertEquals(1, result.getVoteYes());
        assertEquals(0, result.getVoteNo());

    }

    @Test(expected = BusinessException.class)
    public void testMustVoteResultSessionOpenError() {
        //(SETUP)
        when(votingSessionService.getVotingSession(any())).thenReturn(VotingSessionBuilder.newVotingSessionEntity());
        when(pautaService.findPauta(anyInt())).thenReturn(PautaBuilder.newPautaEntity());

        //(ACT)
        ResultVotingResponse result = voteService.resultVoting(PautaBuilder.newPautaEntity().getIdPauta());


        //(ASSERT)
        assertNull(result);


    }


    @Test
    public void testMustSendValidCpfVoteSuccess() {
        //(SETUP)
        String cpf = "725.780.400-10";

        when(inverTextoApiClient.validCpf(any(), any(), any())).thenReturn(ValidResponse.builder().valid(true).build());

        //(ACT)
        ValidResponse validResponse = voteService.validCpf(cpf);

        //(ASSERT)
        assertNotNull(validResponse);
        assertTrue(validResponse.isValid());

    }

    @Test
    public void testMustSendNotValidCpfVoteSuccess() {
        //(SETUP)
        String cpf = "725.780.400-12";

        when(inverTextoApiClient.validCpf(any(), any(), any())).thenReturn(ValidResponse.builder().valid(false).build());

        //(ACT)
        ValidResponse validResponse = voteService.validCpf(cpf);

        //(ASSERT)
        assertNotNull(validResponse);
        assertFalse(validResponse.isValid());

    }

    @Test
    public void testMustStatusAbleToVoteSuccess() {
        //(SETUP)
        String cpf = "725.780.400-10";
        when(inverTextoApiClient.validCpf(any(), any(), any())).thenReturn(ValidResponse.builder().valid(true).build());

        //(ACT)
        ValidCpfResponse status = voteService.status(cpf);

        //(ASSERT)
        assertNotNull(status);
        assertEquals(Status.ABLE_TO_VOTE, status.getStatus());

    }

    @Test
    public void testMustStatusUnableToVoteSuccess() {
        //(SETUP)
        String cpf = "725.780.400-12";
        when(inverTextoApiClient.validCpf(any(), any(), any())).thenReturn(ValidResponse.builder().valid(false).build());

        //(ACT)
        ValidCpfResponse status = voteService.status(cpf);

        //(ASSERT)
        assertNotNull(status);
        assertEquals(Status.UNABLE_TO_VOTE, status.getStatus());

    }
}