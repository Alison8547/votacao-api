package com.br.voting.service;

import com.br.voting.dto.request.VotingSessionRequest;
import com.br.voting.dto.response.VotingSessionResponse;
import jakarta.transaction.Transactional;

public interface VotingSessionService {

    @Transactional
    VotingSessionResponse openSession(Integer idPauta, VotingSessionRequest votingSessionRequest);
}
