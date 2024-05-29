package com.emsi.salesmasterbe2.payload.response;


import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.daos.ProduitQauntiteDao;
import com.emsi.salesmasterbe2.entities.Statut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenteResponse {
    private Long venteId;
    private LocalDate dateVente;
    private Statut statut;
    private double total;
}
