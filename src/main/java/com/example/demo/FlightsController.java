package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightsController {

    @GetMapping("/flights")
    public String defaultEndpoint() {
        return "";
    }

    @PostMapping("/flights/tickets/total")
    public String ticketTotalStringLiteral(@RequestBody FlightsService body) {

        int result = 0;
        for (FlightsService.Tickets ticket: body.getTickets())
            result += ticket.getPrice();

        return String.format("{ result: %d }", result);
    }

}


