package com.br.voting.builders;

import com.br.voting.domain.Pauta;
import com.br.voting.dto.request.PautaRequest;
import com.br.voting.dto.response.PautaResponse;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PautaBuilder {

    public static Pauta newPautaEntity() {
        return Pauta.builder()
                .idPauta(1)
                .pauta("pauta 2.4")
                .createdDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
    }


    public static Pauta newPautaEntityNoSession() {
        return Pauta.builder()
                .idPauta(7)
                .pauta("pauta 2.7")
                .build();
    }

    public static PautaResponse newPautaResponse() {
        return PautaResponse.builder()
                .idPauta(1)
                .pauta("pauta 2.4")
                .build();
    }

    public static PautaRequest newPautaRequest() {
        return PautaRequest.builder()
                .pauta("pauta 2.4")
                .build();
    }
}

