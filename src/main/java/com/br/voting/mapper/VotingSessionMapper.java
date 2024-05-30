package com.br.voting.mapper;

import com.br.voting.domain.VotingSession;
import com.br.voting.dto.request.VotingSessionRequest;
import com.br.voting.dto.response.VotingSessionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotingSessionMapper {

    private final ModelMapper mapper;

    public VotingSession toVotingSession(VotingSessionRequest votingSessionRequest) {
        return mapper.map(votingSessionRequest, VotingSession.class);
    }

    public VotingSessionResponse toVotingSessionResponse(VotingSession votingSession) {
        return mapper.map(votingSession, VotingSessionResponse.class);
    }
}
