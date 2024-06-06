package com.br.voting.mapper;

import com.br.voting.builders.VoteBuilder;
import com.br.voting.domain.Vote;
import com.br.voting.domain.enums.MessageVote;
import com.br.voting.dto.request.VoteRequest;
import com.br.voting.dto.response.VoteResponse;
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
public class VoteMapperTest {

    @InjectMocks
    private VoteMapper voteMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testMustToVoteWithSuccess() {
        VoteRequest voteRequest = new VoteRequest("943.657.200-07", "Sim");

        when(modelMapper.map(voteRequest, Vote.class)).thenReturn(VoteBuilder.newVoteEntity());

        Vote vote = voteMapper.toVote(voteRequest);

        assertNotNull(vote);
        assertEquals("943.657.200-07", vote.getCpfAssociate());
    }

    @Test
    public void testMustToVoteResponseWithSuccess() {
        Vote vote = VoteBuilder.newVoteEntity();

        when(modelMapper.map(vote, VoteResponse.class)).thenReturn(VoteBuilder.newVoteResponse());

        VoteResponse voteResponse = voteMapper.toVoteResponse(vote);

        assertNotNull(voteResponse);
        assertEquals(MessageVote.SIM, voteResponse.getMessageVote());
    }


}
