package com.br.voting.controller;

import com.br.voting.dto.request.PautaRequest;
import com.br.voting.dto.response.PautaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/pauta")
public interface PautaController {

    @Operation(summary = "Criar uma pauta", description = "Salvar uma pauta no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Criou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Foi gerada uma exceção"),
                    @ApiResponse(responseCode = "500", description = "Foi gerado um internal error")
            }
    )
    @PostMapping("/create")
    ResponseEntity<PautaResponse> createPauta(@Valid @RequestBody PautaRequest pautaRequest);
}
