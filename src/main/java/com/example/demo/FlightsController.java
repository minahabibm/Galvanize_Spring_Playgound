package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FlightsController {
    @GetMapping("/")
    public String defaultEndpoint() {
        return "";
    }

    @PostMapping("/flights/tickets/total")
    public FlightsService ticketTotalStringLiteral(@RequestBody FlightsService body) {



        Map <String, Integer> map = new HashMap<String, Integer>();
        map.put("result", 350);
        return body;
    }
}
