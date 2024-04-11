package com.emsi.salesmasterbe2.daos;

import com.emsi.salesmasterbe2.entities.Role;

public class UtilisateurDao {
    private Long utilisateurId;
    private String nom;
    private String email;
    private String motDePasse;
    private Role role;

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }
}
