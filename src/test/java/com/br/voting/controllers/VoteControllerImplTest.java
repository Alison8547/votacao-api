package com.br.voting.controllers;

import com.br.voting.builders.VoteBuilder;
import com.br.voting.controller.VoteControllerImpl;
import com.br.voting.service.VoteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VoteControllerImpl.class)
public class VoteControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VoteServiceImpl voteService;


    @Test
    public void testMustVoteEndPointWithSuccess() throws Exception {

        when(voteService.vote(anyInt(), any())).thenReturn(VoteBuilder.newVoteResponse());

        mockMvc.perform(MockMvcRequestBuilders.post("/vote/register/{idPauta}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(VoteBuilder.newVoteYesRequest())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageVote").value("SIM"));

    }

    @Test
    public void testMustResultVotingEndPointWithSuccess() throws Exception {
        when(voteService.resultVoting(anyInt())).thenReturn(VoteBuilder.newResultVotingResponse());

        mockMvc.perform(MockMvcRequestBuilders.get("/vote/result/{idPauta}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(VoteBuilder.newResultVotingResponse())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.voteYes").value(VoteBuilder.newResultVotingResponse().getVoteYes()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.voteNo").value(VoteBuilder.newResultVotingResponse().getVoteNo()));


    }

    @Test
    public void testMustValidCpfEndPointExternalWithSuccess() throws Exception {
        when(voteService.status(any())).thenReturn(VoteBuilder.newValidCpfResponse());

        mockMvc.perform(MockMvcRequestBuilders.get("/vote/users/{cpf}", "657.546.560-92")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(VoteBuilder.newValidCpfResponse())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ABLE_TO_VOTE"));


    }


}
