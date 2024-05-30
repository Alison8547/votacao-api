package com.br.voting.mapper;

import com.br.voting.domain.Vote;
import com.br.voting.dto.request.VoteRequest;
import com.br.voting.dto.response.VoteResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteMapper {

    private final ModelMapper mapper;


    public Vote toVote(VoteRequest voteRequest) {
        return mapper.map(voteRequest, Vote.class);
    }

    public VoteResponse toVoteResponse(Vote vote) {
        return mapper.map(vote, VoteResponse.class);
    }
}
