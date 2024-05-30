package com.br.voting.controller;

import com.br.voting.dto.request.VotingSessionRequest;
import com.br.voting.dto.response.VotingSessionResponse;
import com.br.voting.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VotingSessionControllerImpl implements VotingSessionController {

    private final VotingSessionService service;

    @Override
    public ResponseEntity<VotingSessionResponse> createVotingSession(Integer idPauta, VotingSessionRequest votingSessionRequest) {
        return new ResponseEntity<>(service.openSession(idPauta, votingSessionRequest), HttpStatus.CREATED);
    }
}
