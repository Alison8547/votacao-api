package com.br.voting.controllers;

import com.br.voting.builders.PautaBuilder;
import com.br.voting.controller.PautaControllerImpl;
import com.br.voting.dto.request.PautaRequest;
import com.br.voting.service.PautaServiceImpl;
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
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PautaControllerImpl.class)
public class PautaControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaServiceImpl pautaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void mustCreatePautaEndPointSuccessfully() throws Exception {

        when(pautaService.createPauta(any())).thenReturn(PautaBuilder.newPautaResponse());


        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/pauta/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(PautaBuilder.newPautaRequest())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPauta").value(PautaBuilder.newPautaResponse().getIdPauta()));

        assertNotNull(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void mustCreatePautaEndPointWithErrorNullPauta() throws Exception {
        PautaRequest pautaRequest = PautaBuilder.newPautaRequest();
        pautaRequest.setPauta(null);

        when(pautaService.createPauta(any())).thenReturn(PautaBuilder.newPautaResponse());

        mockMvc.perform(MockMvcRequestBuilders.post("/pauta/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pautaRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
