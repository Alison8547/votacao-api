package com.br.voting.repository;

import com.br.voting.domain.Pauta;
import com.br.voting.domain.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Integer> {

    Optional<VotingSession> findByPauta(Pauta pauta);
}
