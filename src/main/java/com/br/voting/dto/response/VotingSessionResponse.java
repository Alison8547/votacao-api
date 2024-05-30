package com.br.voting.dto.response;

import com.br.voting.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingSessionResponse {

    private Integer idVotingSession;
    private LocalDateTime closeSession;
    private Pauta pautaVoting;

}
