package ru.tinkoff.academy.landscape.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SystemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void liveness() throws Exception {
        var path = "/system/liveness";

        ResultActions response = mockMvc.perform(get(path));

        response.andExpect(status().isOk());
    }

    @Test
    void readiness() throws Exception {
        var path = "/system/readiness";

        ResultActions response = mockMvc.perform(get(path));

        response.andExpect(status().isOk()).andExpect(content().json("{'LandscapeService': 'OK'}"));
    }
}