package com.br.voting.controller;

import com.br.voting.dto.request.VoteRequest;
import com.br.voting.dto.response.ResultVotingResponse;
import com.br.voting.dto.response.ValidCpfResponse;
import com.br.voting.dto.response.VoteResponse;
import com.br.voting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

    private final VoteService voteService;

    @Override
    public ResponseEntity<VoteResponse> registerVote(Integer idPauta, VoteRequest voteRequest) {
        return new ResponseEntity<>(voteService.vote(idPauta, voteRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResultVotingResponse> resultVoting(Integer idPauta) {
        return new ResponseEntity<>(voteService.resultVoting(idPauta), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ValidCpfResponse> validCpf(String cpf) {
        return new ResponseEntity<>(voteService.status(cpf), HttpStatus.OK);
    }
}
