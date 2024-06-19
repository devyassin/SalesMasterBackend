package com.emsi.salesmasterbe2.controllers;


import com.emsi.salesmasterbe2.payload.response.CardDataStatsResponse;
import com.emsi.salesmasterbe2.services.impl.DashboardServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardServiceImpl dashboardService;

    @GetMapping("/cards")
    public ResponseEntity<CardDataStatsResponse> getcardsData() {
           CardDataStatsResponse cardDataStatsResponse= dashboardService.getCardDataStats();
            return new ResponseEntity<>(cardDataStatsResponse, HttpStatus.OK);
    }
}
