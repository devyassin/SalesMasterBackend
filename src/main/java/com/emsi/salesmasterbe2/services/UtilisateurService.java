package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.UtilisateurDao;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDao saveUtilisateur(UtilisateurDao utilisateurDao);
    UtilisateurDao getUtilisateurById(Long id);
    List<UtilisateurDao> getAllUtilisateurs();
    void deleteUtilisateur(Long id);
}
