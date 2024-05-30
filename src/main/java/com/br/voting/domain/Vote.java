package com.br.voting.domain;

import com.br.voting.domain.enums.MessageVote;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VOTE")
public class Vote implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String cpfAssociate;

    @Column(name = "date_vote")
    private LocalDateTime dateVote;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_vote")
    private MessageVote messageVote;

    @ManyToOne
    @JoinColumn(name = "id_voting_session")
    private VotingSession votingSession;
}
