package com.br.voting.repository;

import com.br.voting.domain.Vote;
import com.br.voting.domain.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {

    boolean existsByVotingSessionAndCpfAssociate(VotingSession votingSession, String cpfAssociate);
}
