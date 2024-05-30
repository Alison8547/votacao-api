package com.br.voting.service;

import com.br.voting.dto.request.PautaRequest;
import com.br.voting.dto.response.PautaResponse;
import jakarta.transaction.Transactional;

public interface PautaService {

    @Transactional
    PautaResponse createPauta(PautaRequest pautaRequest);
}
