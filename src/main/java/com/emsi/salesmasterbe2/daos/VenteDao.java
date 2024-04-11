package com.emsi.salesmasterbe2.daos;

import com.emsi.salesmasterbe2.entities.Statut;

import java.util.Date;

public class VenteDao {
    private Long venteId;
    private Date dateVente;
    private Statut statut;
    private double total;
    private ClientDao client;

    public VenteDao() {
    }

    public Long getVenteId() {
        return venteId;
    }

    public void setVenteId(Long venteId) {
        this.venteId = venteId;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = Statut.valueOf(statut);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ClientDao getClient() {
        return client;
    }

    public void setClient(ClientDao client) {
        this.client = client;
    }
}
