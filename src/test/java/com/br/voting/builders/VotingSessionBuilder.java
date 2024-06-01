package com.br.voting.builders;

import com.br.voting.domain.Vote;
import com.br.voting.domain.VotingSession;
import com.br.voting.dto.request.VotingSessionRequest;
import com.br.voting.dto.response.VotingSessionResponse;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VotingSessionBuilder {
    private static final Set<Vote> votes = new HashSet<>(List.of(VoteBuilder.newVoteEntity()));

    public static VotingSession newVotingSessionEntity() {
        return VotingSession.builder()
                .idVotingSession(2)
                .openSession(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .closeSession(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).plusMinutes(3))
                .votes(votes)
                .pauta(PautaBuilder.newPautaEntity())
                .build();
    }

    public static VotingSession newVotingSessionEntityBefore() {
        return VotingSession.builder()
                .idVotingSession(2)
                .openSession(LocalDateTime.of(2023, 3, 11, 12, 22, 33).truncatedTo(ChronoUnit.SECONDS))
                .closeSession(LocalDateTime.of(2023, 3, 14, 12, 22, 33).truncatedTo(ChronoUnit.SECONDS))
                .votes(votes)
                .pauta(PautaBuilder.newPautaEntity())
                .build();
    }

    public static VotingSession newVotingSessionEntityBeforeDataOpen() {
        return VotingSession.builder()
                .idVotingSession(2)
                .openSession(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .closeSession(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).minusHours(2))
                .votes(votes)
                .pauta(PautaBuilder.newPautaEntity())
                .build();
    }

    public static VotingSession newVotingSessionEntityDefaultTime() {
        return VotingSession.builder()
                .idVotingSession(2)
                .openSession(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .closeSession(null)
                .votes(votes)
                .pauta(PautaBuilder.newPautaEntity())
                .build();
    }

    public static VotingSessionRequest newVotingSessionEntityDefaultTimeRequest() {
        return VotingSessionRequest.builder()
                .closeSession(null)
                .build();
    }

    public static VotingSessionResponse newVotingSessionResponse() {
        return VotingSessionResponse.builder()
                .idVotingSession(2)
                .pautaVoting(PautaBuilder.newPautaEntity())
                .closeSession(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).plusMinutes(3))
                .build();
    }

    public static VotingSessionRequest newVotingSessionRequest() {
        return VotingSessionRequest.builder()
                .closeSession(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).plusMinutes(3))
                .build();
    }
}
