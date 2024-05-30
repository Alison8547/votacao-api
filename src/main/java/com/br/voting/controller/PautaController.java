package com.br.voting.controller;

import com.br.voting.dto.request.PautaRequest;
import com.br.voting.dto.response.PautaResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/pauta")
public interface PautaController {

    @PostMapping("/create")
    ResponseEntity<PautaResponse> createPauta(@Valid @RequestBody PautaRequest pautaRequest);
}
