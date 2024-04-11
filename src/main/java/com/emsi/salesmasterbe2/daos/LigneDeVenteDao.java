package com.emsi.salesmasterbe2.daos;

public class LigneDeVenteDao {
    Long ligneDeVenteId;
    private VenteDao vente;
    private ProduitDao produit;
    private int quantite;
    private double prixUnitaire;

    public Long getLigneDeVenteId() {
        return ligneDeVenteId;
    }

    public void setLigneDeVenteId(Long ligneDeVenteId) {
        this.ligneDeVenteId = ligneDeVenteId;
    }

    public VenteDao getVente() {
        return vente;
    }

    public void setVente(VenteDao vente) {
        this.vente = vente;
    }

    public ProduitDao getProduit() {
        return produit;
    }

    public void setProduit(ProduitDao produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
