package com.thoughtworks.rslist.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class RsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetRsList() throws Exception {
        mockMvc.perform(get("/rs/list"))
                .andExpect(content().string("[第一条事件, 第二条事件, 第三条事件]"))
                .andExpect(status().isOk());
    }
}