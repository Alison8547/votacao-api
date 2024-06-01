package com.br.voting.builders;

import com.br.voting.domain.Vote;
import com.br.voting.domain.enums.MessageVote;
import com.br.voting.dto.request.VoteRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class VoteBuilder {
    public static Vote newVoteEntity() {
        return Vote.builder()
                .votingSession(VotingSessionBuilder.newVotingSessionEntity())
                .dateVote(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .cpfAssociate("943.657.200-07")
                .messageVote(MessageVote.SIM)
                .build();

    }


    public static VoteRequest newVoteRequest() {
        return VoteRequest.builder()
                .cpfAssociate("943.657.200-07")
                .messageVote("Sim")
                .build();
    }

    public static VoteRequest newVoteNewCpfRequest() {
        return VoteRequest.builder()
                .cpfAssociate("501.980.140-23")
                .messageVote("Sim")
                .build();
    }

    public static VoteRequest newVoteNoRequest() {
        return VoteRequest.builder()
                .cpfAssociate("943.657.200-07")
                .messageVote("Não")
                .build();
    }

    public static VoteRequest newVoteValidMessageRequest() {
        return VoteRequest.builder()
                .cpfAssociate("943.657.200-07")
                .messageVote("outro valor")
                .build();
    }
}
