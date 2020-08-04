package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.domain.RsEvent;
import com.thoughtworks.rslist.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class RsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetRsEventList() throws Exception {
        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$[0].eventName", is("第一条事件")))
                .andExpect(jsonPath("$[0].keyWord", is("无分类")))
                .andExpect(jsonPath("$[0]", not(hasKey("user"))))
                .andExpect(jsonPath("$[1].eventName", is("第二条事件")))
                .andExpect(jsonPath("$[1].keyWord", is("无分类")))

                .andExpect(jsonPath("$[1]", not(hasKey("user"))))
                .andExpect(jsonPath("$[2].eventName", is("第三条事件")))
                .andExpect(jsonPath("$[2].keyWord", is("无分类")))
                .andExpect(jsonPath("$[2]", not(hasKey("user"))))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetRsEventBetween() throws Exception {
        mockMvc.perform(get("/rs/list?start=1&end=2"))
                .andExpect(jsonPath("$[0].eventName", is("第一条事件")))
                .andExpect(jsonPath("$[0].keyWord", is("无分类")))
                .andExpect(jsonPath("$[1].eventName", is("第二条事件")))
                .andExpect(jsonPath("$[1].keyWord", is("无分类")))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rs/list?start=2&end=3"))
                .andExpect(jsonPath("$[0].eventName", is("第二条事件")))
                .andExpect(jsonPath("$[0].keyWord", is("无分类")))
                .andExpect(jsonPath("$[1].eventName", is("第三条事件")))
                .andExpect(jsonPath("$[1].keyWord", is("无分类")))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rs/list?start=1&end=3"))
                .andExpect(jsonPath("$[0].eventName", is("第一条事件")))
                .andExpect(jsonPath("$[0].keyWord", is("无分类")))
                .andExpect(jsonPath("$[1].eventName", is("第二条事件")))
                .andExpect(jsonPath("$[1].keyWord", is("无分类")))
                .andExpect(jsonPath("$[2].eventName", is("第三条事件")))
                .andExpect(jsonPath("$[2].keyWord", is("无分类")))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetOneRsEvent() throws Exception {
        mockMvc.perform(get("/rs/1"))
                .andExpect(jsonPath("$.eventName", is("第一条事件")))
                .andExpect(jsonPath("$.keyWord", is("无分类")))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rs/2"))
                .andExpect(jsonPath("$.eventName", is("第二条事件")))
                .andExpect(jsonPath("$.keyWord", is("无分类")))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rs/3"))
                .andExpect(jsonPath("$.eventName", is("第三条事件")))
                .andExpect(jsonPath("$.keyWord", is("无分类")))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAddRsEvent() throws Exception {
        String rsEventJson = "{\"eventName\":\"第四条事件\",\"keyWord\":\"无分类\",\"user\":{\"name\":\"Alibaba\",\"gender\":\"male\",\"age\":20,\"email\":\"ab.com\",\"phone\":\"11234567890\",\"vote\":10}}";
        mockMvc.perform(post("/rs/event").content(rsEventJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$[0].eventName", is("第一条事件")))
                .andExpect(jsonPath("$[0].keyWord", is("无分类")))
                .andExpect(jsonPath("$[1].eventName", is("第二条事件")))
                .andExpect(jsonPath("$[1].keyWord", is("无分类")))
                .andExpect(jsonPath("$[2].eventName", is("第三条事件")))
                .andExpect(jsonPath("$[2].keyWord", is("无分类")))
                .andExpect(jsonPath("$[3].eventName", is("第四条事件")))
                .andExpect(jsonPath("$[3].keyWord", is("无分类")));

    }
}