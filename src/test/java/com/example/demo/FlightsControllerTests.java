package com.example.demo;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightsController.class)
@AutoConfigureMockMvc
public class FlightsControllerTests {

    @Autowired
    private MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void endpointUrlTest() throws Exception {
        this.mvc.perform(get("/flights").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void ticketTotalStringLiteralTest() throws Exception {

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\":[{\"passenger\":{\"firstName\":\"Some name\",\"lastName\":\"Some other name\"},\"price\":200},{\"passenger\":{\"firstName\":\"Name B\",\"lastName\":\"Name C\"},\"price\":150}]}");

        this.mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void ticketTotalSerializingJacksonTest() throws Exception{
        HashMap<String, String> person0 = new HashMap<String, String>(){
            {
                put("firstName", "Hercules");
                put("lastName", "someone");
            }
        };
        HashMap<String, Object> ticket0 = new HashMap<>();
        ticket0.put("passenger", person0);
        ticket0.put("price", 150);

        HashMap<String, String> person1= new HashMap<String, String>(){
            {
                put("firstName", "John");
                put("lastName", "Doe");
            }
        };
        HashMap<String, Object> ticket1 = new HashMap<>();
        ticket1.put("passenger", person1);
        ticket1.put("price", 200);

        List tickets = new ArrayList<>();
        tickets.add(ticket0);
        tickets.add(ticket1);

        HashMap<String, List> list = new HashMap<>();
        list.put("tickets",tickets);

        String json = objectMapper.writeValueAsString(list);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("result", is(350)));
    }

    @Test
    public void ticketTotalFileFixturesTest() throws Exception{

        String json = getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("result", is(350)));
    }

    private String getJSON(String path) throws Exception {
        final URL jsonResourceLocation = this.getClass().getResource(path);
        assertNotNull(jsonResourceLocation, "json path resource location is null.");
        File f = new File(jsonResourceLocation.getPath());
        final String jsonString = Files.readString(f.toPath());
        return jsonString;
    }


}