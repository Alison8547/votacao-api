package com.br.voting.service;

import com.br.voting.domain.Pauta;
import com.br.voting.domain.Vote;
import com.br.voting.domain.VotingSession;
import com.br.voting.domain.enums.MessageVote;
import com.br.voting.domain.enums.Status;
import com.br.voting.dto.request.VoteRequest;
import com.br.voting.dto.response.ResultVotingResponse;
import com.br.voting.dto.response.ValidCpfResponse;
import com.br.voting.dto.response.ValidResponse;
import com.br.voting.dto.response.VoteResponse;
import com.br.voting.exception.BusinessException;
import com.br.voting.infrastructure.client.invertexto.InverTextoApiClient;
import com.br.voting.mapper.VoteMapper;
import com.br.voting.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final PautaServiceImpl pautaService;
    private final VotingSessionServiceImpl votingSessionService;
    private final InverTextoApiClient inverTextoApiClient;
    private final static String TOKEN_ACCESS = "7958|c9xWcEiBQl9ijubo9WhN5CrgW3PFvuyd";
    private final static String TYPE_CPF = "cpf";


    @Override
    public VoteResponse vote(Integer idPauta, VoteRequest voteRequest) {
        VotingSession votingSession = votingSessionService.getVotingSession(pautaService.findPauta(idPauta));
        Vote vote = voteMapper.toVote(voteRequest);

        if (LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).isAfter(votingSession.getCloseSession())) {
            throw new BusinessException("Voting session closed!");
        }

        if (voteRequest.getMessageVote().equalsIgnoreCase("Sim")) {
            vote.setMessageVote(MessageVote.SIM);
        } else if (voteRequest.getMessageVote().equalsIgnoreCase("Não")) {
            vote.setMessageVote(MessageVote.NAO);
        } else {
            throw new BusinessException("Incorrect value entered! Vote Sim or Não");
        }
        vote.setVotingSession(votingSession);
        vote.setDateVote(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        if (voteRepository.existsByVotingSessionAndCpfAssociate(votingSession, voteRequest.getCpfAssociate())) {
            throw new BusinessException("CPF Associate has already registered to vote on this Pauta!");
        }

        voteRepository.save(vote);
        log.info("Voting completed! Pauta: {} Associate: {} Date Vote: {}", votingSession.getPauta().getPauta(), vote.getCpfAssociate(), vote.getDateVote());

        return voteMapper.toVoteResponse(vote);
    }

    @Override
    public ResultVotingResponse resultVoting(Integer idPauta) {
        Pauta pauta = pautaService.findPauta(idPauta);
        Set<Vote> votes = votingSessionService.getVotingSession(pauta).getVotes();

        if (LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).isBefore(votingSessionService.getVotingSession(pauta).getCloseSession())) {
            throw new BusinessException("Session is still open! it is not possible to see or send the voting results");
        }

        long countYes = votes.stream().filter(x -> x.getMessageVote().equals(MessageVote.SIM)).count();
        long countNo = votes.stream().filter(x -> x.getMessageVote().equals(MessageVote.NAO)).count();

        return ResultVotingResponse.builder()
                .pauta(pauta.getPauta())
                .voteYes(countYes)
                .voteNo(countNo)
                .build();
    }

    @Override
    public ValidResponse validCpf(String cpf) {
        return inverTextoApiClient.validCpf(TOKEN_ACCESS, cpf, TYPE_CPF);
    }


    @Override
    public ValidCpfResponse status(String cpf) {
        ValidResponse validResponse = validCpf(cpf);
        if (validResponse.isValid()) {
            return ValidCpfResponse.builder().status(Status.ABLE_TO_VOTE).build();
        } else {
            return ValidCpfResponse.builder().status(Status.UNABLE_TO_VOTE).build();
        }
    }
}
