package com.emsi.salesmasterbe2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id")
    private Long produitId;

    @NotBlank(message = "Nom est obligatoire")
    @Column(name = "nom")
    private String nom;

    @Lob
    @NotBlank(message = "Description est obligatoire")
    @Column(name = "description")
    private String description;

    @Min(value = 0, message = "Le prix doit être positive")
    @Column(name = "prix")
    private double prix;

    @Min(value = 0, message = "La quantité en stock doit être positive")
    @Column(name = "quantite_en_stock")
    private int quantiteEnStock;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "produit")
    private List<LigneDeVente> lignesDeVente;


}
