package com.emsi.salesmasterbe2.daos;
import com.emsi.salesmasterbe2.entities.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class FactureDao {
    private Long factureId;
    private Date dateFacturation;
    private double montantTotal;
    private Statut statutPaiement;
    private VenteDao vente;

}
