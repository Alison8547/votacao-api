package com.br.voting.service;

import com.br.voting.domain.Pauta;
import com.br.voting.dto.request.PautaRequest;
import com.br.voting.dto.response.PautaResponse;
import com.br.voting.exception.BusinessException;
import com.br.voting.mapper.PautaMapper;
import com.br.voting.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    @Override
    public PautaResponse createPauta(PautaRequest pautaRequest) {
        Pauta pauta = pautaMapper.toPauta(pautaRequest);
        pauta.setCreatedDate(LocalDateTime.now());

        pautaRepository.save(pauta);
        log.info("Pauta created successfully! -> Id {}, {}, Created Date: {}", pauta.getIdPauta(), pauta.getPauta(), pauta.getCreatedDate());

        return pautaMapper.toPautaResponse(pauta);
    }

    public Pauta findPauta(Integer idPauta) {
        return pautaRepository.findById(idPauta).orElseThrow(() -> new BusinessException("Pauta not found!"));
    }

}
