package com.br.voting.mapper;

import com.br.voting.domain.Pauta;
import com.br.voting.dto.request.PautaRequest;
import com.br.voting.dto.response.PautaResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PautaMapper {

    private final ModelMapper mapper;

    public Pauta toPauta(PautaRequest pautaRequest) {
        return mapper.map(pautaRequest, Pauta.class);
    }

    public PautaResponse toPautaResponse(Pauta pauta) {
        return mapper.map(pauta, PautaResponse.class);
    }
}
