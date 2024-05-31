package com.br.voting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingSessionRequest {

    @Schema(example = "2024-05-31T15:30:00", type = "string", description = "Data do fechamento da sessão")
    private LocalDateTime closeSession;
}
