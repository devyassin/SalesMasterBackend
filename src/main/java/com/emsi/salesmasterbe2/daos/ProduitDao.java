package com.emsi.salesmasterbe2.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProduitDao {
    private Long produitId;
    private String nom;
    private String description;
    private double prix;
    private int quantiteEnStock;
    private String image;

}
