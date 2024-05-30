package com.br.voting.service;

import com.br.voting.domain.Pauta;
import com.br.voting.domain.VotingSession;
import com.br.voting.dto.request.VotingSessionRequest;
import com.br.voting.dto.response.VotingSessionResponse;
import com.br.voting.exception.BusinessException;
import com.br.voting.mapper.VotingSessionMapper;
import com.br.voting.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionRepository votingSessionRepository;
    private final VotingSessionMapper votingSessionMapper;
    private final PautaServiceImpl pautaService;
    private static final Integer DEFAULT_TIME_VOTING = 60;

    @Override
    public VotingSessionResponse openSession(Integer idPauta, VotingSessionRequest votingSessionRequest) {
        Pauta pauta = pautaService.findPauta(idPauta);
        VotingSession votingSession = votingSessionMapper.toVotingSession(votingSessionRequest);

        if (votingSessionRepository.findByPauta(pauta).isPresent()) {
            throw new BusinessException("Voting session already exists for this Pauta!");
        }

        votingSession.setOpenSession(LocalDateTime.now());
        votingSession.setPauta(pauta);

        if (votingSession.getCloseSession() == null) {
            votingSession.setCloseSession(LocalDateTime.now().plusSeconds(DEFAULT_TIME_VOTING));
            log.info("Default time in session has been activated! Date Closed Session: {}", votingSession.getCloseSession());
        }

        if (votingSession.getCloseSession().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Closing date cannot be before the Session opening date!");
        }

        votingSessionRepository.save(votingSession);
        log.info("Session created successfully! idVotingSession {},  Date Open Session: {}", votingSession.getIdVotingSession(), votingSession.getOpenSession());

        return votingSessionMapper.toVotingSessionResponse(votingSession);
    }

    public VotingSession getVotingSession(Pauta pauta) {
        return votingSessionRepository.findByPauta(pauta)
                .orElseThrow(() -> new BusinessException("Session and Pauta not found!"));
    }


}
