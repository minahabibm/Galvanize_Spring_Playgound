package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FlightsController {

    @GetMapping("/flights")
    public String defaultEndpoint() {
        return "";
    }

    @PostMapping("/flights/tickets/total")
    public FlightsResponse ticketTotal(@RequestBody FlightsService body) {
        FlightsResponse flightResponse = new FlightsResponse(body);
        return flightResponse;
    }

}


