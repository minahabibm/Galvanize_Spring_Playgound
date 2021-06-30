package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightsController {

    @GetMapping("/")
    public String defaultEndpoint() {
        return "";
    }

    @PostMapping("/flights/tickets/total")
    public FlightsResponse ticketTotalStringLiteral(@RequestBody FlightsService body) {

        FlightsResponse response = new FlightsResponse(body);

        return response;
    }

}


