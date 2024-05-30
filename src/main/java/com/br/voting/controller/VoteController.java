package com.br.voting.controller;

import com.br.voting.dto.request.VoteRequest;
import com.br.voting.dto.response.ResultVotingResponse;
import com.br.voting.dto.response.ValidCpfResponse;
import com.br.voting.dto.response.VoteResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/vote")
public interface VoteController {

    @PostMapping("/register/{idPauta}")
    ResponseEntity<VoteResponse> registerVote(@PathVariable(name = "idPauta") Integer idPauta, @Valid @RequestBody VoteRequest voteRequest);

    @GetMapping("/result/{idPauta}")
    ResponseEntity<ResultVotingResponse> resultVoting(@PathVariable(name = "idPauta") Integer idPauta);

    @GetMapping("/users/{cpf}")
    ResponseEntity<ValidCpfResponse> validCpf(@PathVariable(name = "cpf") String cpf);
}
