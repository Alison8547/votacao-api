package com.br.voting.controllers;

import com.br.voting.builders.VotingSessionBuilder;
import com.br.voting.controller.VotingSessionControllerImpl;
import com.br.voting.service.VotingSessionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VotingSessionControllerImpl.class)
public class VotingSessionControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VotingSessionServiceImpl votingSessionService;


    @Test
    public void testMustCreateVotingSessionEndPointWithSuccess() throws Exception {
        when(votingSessionService.openSession(anyInt(), any())).thenReturn(VotingSessionBuilder.newVotingSessionResponse());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/voting-session/create/{idPauta}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(VotingSessionBuilder.newVotingSessionRequest())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idVotingSession").value(2));


        assertNotNull(resultActions.andReturn().getResponse().getContentAsString());
    }


}

