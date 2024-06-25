package com.emsi.salesmasterbe2.controllers;


import com.emsi.salesmasterbe2.payload.response.CardDataStatsResponse;
import com.emsi.salesmasterbe2.payload.response.StockStatusCountResponse;
import com.emsi.salesmasterbe2.payload.response.VenteStatusCountResponse;
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

    @GetMapping("/ventes_count_stats")
    public ResponseEntity<VenteStatusCountResponse> getVenteCount() {
        VenteStatusCountResponse venteStatusCountResponse= dashboardService.getVenteCountStats();
        return new ResponseEntity<>(venteStatusCountResponse, HttpStatus.OK);
    }

    @GetMapping("/stock_count_stats")
    public ResponseEntity<StockStatusCountResponse> getStatusCountStats() {
        StockStatusCountResponse stockStatusCountResponse= dashboardService.getStatusCountStats();
        return new ResponseEntity<>(stockStatusCountResponse, HttpStatus.OK);
    }
}
