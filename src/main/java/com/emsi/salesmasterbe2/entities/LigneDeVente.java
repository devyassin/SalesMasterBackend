package com.emsi.salesmasterbe2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lignes_de_vente")
public class LigneDeVente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ligne_de_vente_id")
    private Long LigneDeVenteId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vente_id")
    private Vente vente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @Min(value = 0, message = "La quantité doit être positive")
    @Column(name = "quantite")
    private int quantite;

    @Column(name = "prix_unitaire")
    private double prixUnitaire;


}
