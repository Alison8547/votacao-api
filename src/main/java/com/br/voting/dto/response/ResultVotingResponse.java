package com.br.voting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultVotingResponse {
    private String pauta;
    private Long voteYes;
    private Long voteNo;
}
