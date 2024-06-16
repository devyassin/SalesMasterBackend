package com.emsi.salesmasterbe2.payload.response;

import com.emsi.salesmasterbe2.daos.VenteDao;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactureDetails {
    private Long factureId;
    private LocalDate dateFacturation;
    private double montantTotal;
    private String PDF;
    private VenteResponseDetails vente;
}
