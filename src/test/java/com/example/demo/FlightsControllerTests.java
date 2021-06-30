package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightsController.class)
@AutoConfigureMockMvc
public class FlightsControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void endpoinUrlTest() throws Exception {
        this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void ticketTotalStringLiteralTest() throws Exception {

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\":[{\"passenger\":{\"firstName\":\"Some name\",\"lastName\":\"Some other name\"},\"price\":200},{\"passenger\":{\"firstName\":\"Name B\",\"lastName\":\"Name C\"},\"price\":150}]}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.result", is(350)));
        .andExpect(content().string("hello world"));
    }
}