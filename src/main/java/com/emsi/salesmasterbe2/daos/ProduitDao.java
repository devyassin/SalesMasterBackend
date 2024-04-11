package com.emsi.salesmasterbe2.daos;

public class ProduitDao {
    private Long produitId;
    private String nom;
    private String description;
    private double prix;
    private int quantiteEnStock;
    private String image;

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
