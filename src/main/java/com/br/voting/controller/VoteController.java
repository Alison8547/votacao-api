package com.br.voting.controller;

import com.br.voting.dto.request.VoteRequest;
import com.br.voting.dto.response.ResultVotingResponse;
import com.br.voting.dto.response.ValidCpfResponse;
import com.br.voting.dto.response.VoteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/vote")
public interface VoteController {

    @Operation(summary = "Votar em uma pauta", description = "Votar e registrar no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Votou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Foi gerada uma exceção"),
                    @ApiResponse(responseCode = "500", description = "Foi gerado um internal error")
            }
    )
    @PostMapping("/register/{idPauta}")
    ResponseEntity<VoteResponse> registerVote(@PathVariable(name = "idPauta") Integer idPauta, @Valid @RequestBody VoteRequest voteRequest);

    @Operation(summary = "Pegar o resultado da votação da pauta", description = "Pega o resultado da votação da pauta no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Resgatou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Foi gerada uma exceção"),
                    @ApiResponse(responseCode = "500", description = "Foi gerado um internal error")
            }
    )
    @GetMapping("/result/{idPauta}")
    ResponseEntity<ResultVotingResponse> resultVoting(@PathVariable(name = "idPauta") Integer idPauta);

    @Operation(summary = "Verificar se pode votar com seu CPF", description = "Verificar se seu CPF está apto para votar")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "400", description = "Foi gerada uma exceção"),
                    @ApiResponse(responseCode = "500", description = "Foi gerado um internal error")
            }
    )
    @GetMapping("/users/{cpf}")
    ResponseEntity<ValidCpfResponse> validCpf(@PathVariable(name = "cpf") String cpf);
}
