package com.br.voting.service;

import com.br.voting.dto.request.VoteRequest;
import com.br.voting.dto.response.ResultVotingResponse;
import com.br.voting.dto.response.VoteResponse;
import jakarta.transaction.Transactional;

public interface VoteService {

    @Transactional
    VoteResponse vote(Integer idPauta, VoteRequest voteRequest);

    ResultVotingResponse resultVoting(Integer idPauta);
}
