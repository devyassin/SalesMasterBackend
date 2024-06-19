package com.emsi.salesmasterbe2.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardDataStatsResponse {

    private long totalClients;
    private long totalVentes;
    private long totalUsers;
    private long totalProducts;
    private long totalFactures;

}
