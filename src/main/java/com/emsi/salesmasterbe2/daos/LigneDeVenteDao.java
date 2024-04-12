package com.emsi.salesmasterbe2.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LigneDeVenteDao {
    Long ligneDeVenteId;
    private VenteDao vente;
    private ProduitDao produit;
    private int quantite;
    private double prixUnitaire;

}
