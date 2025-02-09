package com.emsi.salesmasterbe2.daos;


import com.emsi.salesmasterbe2.payload.response.VenteResponseDetails;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class FactureDao {
    private Long factureId;
    private LocalDate dateFacturation;
    private double montantTotal;
    private String PDF;
    private VenteDao vente;
}
