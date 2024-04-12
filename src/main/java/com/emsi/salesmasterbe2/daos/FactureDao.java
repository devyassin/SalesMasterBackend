package com.emsi.salesmasterbe2.daos;
import com.emsi.salesmasterbe2.entities.*;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor @NoArgsConstructor
public class FactureDao {
    private Long factureId;
    private Date dateFacturation;
    private double montantTotal;
    private Statut statutPaiement;
    private VenteDao vente;

}
