package com.br.voting.controller;

import com.br.voting.dto.request.VotingSessionRequest;
import com.br.voting.dto.response.VotingSessionResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/voting-session")
public interface VotingSessionController {

    @PostMapping("/create/{idPauta}")
    ResponseEntity<VotingSessionResponse> createVotingSession(@PathVariable(name = "idPauta") Integer idPauta, @RequestBody @Valid VotingSessionRequest votingSessionRequest);
}
