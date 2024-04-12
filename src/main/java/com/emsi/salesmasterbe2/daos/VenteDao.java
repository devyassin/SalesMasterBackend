package com.emsi.salesmasterbe2.daos;

import com.emsi.salesmasterbe2.entities.Statut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor
public class VenteDao {
    private Long venteId;
    private Date dateVente;
    private Statut statut;
    private double total;
    private ClientDao client;

}
