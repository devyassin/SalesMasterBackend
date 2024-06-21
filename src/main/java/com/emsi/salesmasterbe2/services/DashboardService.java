package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.payload.response.CardDataStatsResponse;
import com.emsi.salesmasterbe2.payload.response.VenteStatusCountResponse;

public interface DashboardService {
    CardDataStatsResponse getCardDataStats();
    VenteStatusCountResponse getVenteCountStats();
}
