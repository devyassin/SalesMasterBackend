package com.emsi.salesmasterbe2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lignes_de_vente")
public class LigneDeVente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ligne_de_vente_id")
    private Long LigneDeVenteId;

    @ManyToOne
    @JoinColumn(name = "vente_id")
    private Vente vente;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @NotBlank(message = "quantite est obligatoire")
    @Column(name = "quantite")
    private int Quantité;

    @Column(name = "prix_unitaire")
    private double PrixUnitaire;


}
