package com.br.voting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {

    @NotBlank
    @CPF(message = "Invalid CPF to vote!")
    @Schema(description = "Cpf do associado para votar", example = "390.434.850-71")
    private String cpfAssociate;

    @NotNull
    @Schema(description = "Voto Sim ou Não", example = "Não")
    private String messageVote;

}
