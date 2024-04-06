package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> getAllUtilisateurs();

    Utilisateur getUtilisateurById(Long id);

    Utilisateur saveUtilisateur(Utilisateur utilisateur);

    void deleteUtilisateur(Long id);
}
