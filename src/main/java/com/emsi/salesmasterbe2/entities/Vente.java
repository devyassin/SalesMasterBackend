package com.emsi.salesmasterbe2.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter @Setter @Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "ventes")
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vente_id")
    private Long venteId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_vente")
    private LocalDate dateVente;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut;

    @Column(name = "total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "vente",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LigneDeVente> lignesDeVentes;

//    @OneToOne
//    private Facture facture;


}
