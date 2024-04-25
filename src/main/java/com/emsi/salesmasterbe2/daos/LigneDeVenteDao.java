package com.emsi.salesmasterbe2.daos;

import lombok.*;

@Getter
@Setter
@ToString
@Builder @AllArgsConstructor @NoArgsConstructor
public class LigneDeVenteDao {
    Long ligneDeVenteId;
    private VenteDao vente;
    private ProduitDao produit;
    private int quantite;
    private double prixUnitaire;

}
