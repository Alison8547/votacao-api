package com.br.voting.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "VOTING_SESSION")
public class VotingSession implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idVotingSession;

    @Column(name = "open_session")
    private LocalDateTime openSession;

    @Column(name = "close_session")
    private LocalDateTime closeSession;

    @OneToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "votingSession", cascade = CascadeType.ALL)
    private Set<Vote> votes = new LinkedHashSet<>();

}
