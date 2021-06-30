package com.example.demo;

public class FlightsResponse {
    int result;

    FlightsResponse(FlightsService jsonReq){
        result = 0;
        for (FlightsService.Tickets ticket: jsonReq.getTickets())
            result += ticket.getPrice();
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
