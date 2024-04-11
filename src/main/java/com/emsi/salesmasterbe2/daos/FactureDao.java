package com.emsi.salesmasterbe2.daos;
import com.emsi.salesmasterbe2.entities.*;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
public class FactureDao {
    private Long factureId;
    private Date dateFacturation;
    private double montantTotal;
    private Statut statutPaiement;
    private VenteDao vente;

    public FactureDao() {
    }

    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    public Date getDateFacturation() {
        return dateFacturation;
    }

    public void setDateFacturation(Date dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public @NotNull(message = "statut paiement est obligatoire") com.emsi.salesmasterbe2.entities.Statut getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(String statutPaiement) {
        this.statutPaiement = Statut.valueOf(statutPaiement);
    }

    public VenteDao getVente() {
        return vente;
    }

    public void setVente(VenteDao vente) {
        this.vente = vente;
    }
}
