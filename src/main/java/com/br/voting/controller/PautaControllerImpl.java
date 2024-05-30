package com.br.voting.controller;

import com.br.voting.dto.request.PautaRequest;
import com.br.voting.dto.response.PautaResponse;
import com.br.voting.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PautaControllerImpl implements PautaController {

    private final PautaService pautaService;

    @Override
    public ResponseEntity<PautaResponse> createPauta(PautaRequest pautaRequest) {
        return new ResponseEntity<>(pautaService.createPauta(pautaRequest), HttpStatus.CREATED);
    }
}
