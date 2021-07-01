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
    public Map<String, Integer> ticketTotalStringLiteral(@RequestBody FlightsService body) {
        int price=0;
        for (FlightsService.Tickets fl: body.getTickets()) {
            price+=fl.getPrice();
        }
//        int result = 0;
//        for (FlightsService.Tickets ticket: body.getTickets())
//            result += ticket.getPrice();
//
//        return String.format("{ result: %d }", result);
        Map<String, Integer> map = new HashMap<>();
        map.put("result",price);
        return map;
    }

}


