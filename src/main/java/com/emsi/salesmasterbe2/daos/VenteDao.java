package com.emsi.salesmasterbe2.daos;

import com.emsi.salesmasterbe2.entities.Statut;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@ToString
@Builder @AllArgsConstructor @NoArgsConstructor
public class VenteDao {
    private Long venteId;
    private LocalDate dateVente;
    private Statut statut;
    private double total;
    private ClientDao client;

}
