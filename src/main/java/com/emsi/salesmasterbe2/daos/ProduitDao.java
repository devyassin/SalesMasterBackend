package com.emsi.salesmasterbe2.daos;

import lombok.*;

@Getter
@Setter
@ToString
@Builder @AllArgsConstructor @NoArgsConstructor
public class ProduitDao {
    private Long produitId;
    private String nom;
    private String description;
    private double prix;
    private int quantiteEnStock;
    private String image;

}
