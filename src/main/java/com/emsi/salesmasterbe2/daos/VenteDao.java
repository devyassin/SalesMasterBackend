package com.emsi.salesmasterbe2.daos;

import com.emsi.salesmasterbe2.entities.Statut;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@Builder @AllArgsConstructor @NoArgsConstructor
public class VenteDao {
    private Long venteId;
    private Date dateVente;
    private Statut statut;
    private double total;
    private ClientDao client;

}
