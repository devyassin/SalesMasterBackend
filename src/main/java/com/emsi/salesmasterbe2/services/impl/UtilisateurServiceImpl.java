package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.UtilisateurDao;
import com.emsi.salesmasterbe2.entities.Role;
import com.emsi.salesmasterbe2.entities.Utilisateur;
import com.emsi.salesmasterbe2.repository.UtilisateurRepository;
import com.emsi.salesmasterbe2.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDao saveUtilisateur(UtilisateurDao utilisateurDao) {
        Utilisateur utilisateurEntity = convertToUtilisateurEntity(utilisateurDao);
        utilisateurEntity = utilisateurRepository.save(utilisateurEntity);
        return convertToUtilisateurDao(utilisateurEntity);
    }

    @Override
    public UtilisateurDao getUtilisateurById(Long id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        return utilisateurOptional.map(this::convertToUtilisateurDao).orElse(null);
    }

    @Override
    public List<UtilisateurDao> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs.stream().map(this::convertToUtilisateurDao).collect(Collectors.toList());
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    private Utilisateur convertToUtilisateurEntity(UtilisateurDao utilisateurDao) {
        Utilisateur utilisateurEntity = new Utilisateur();
        utilisateurEntity.setUtilisateurID(utilisateurDao.getUtilisateurId());
        utilisateurEntity.setNom(utilisateurDao.getNom());
        utilisateurEntity.setEmail(utilisateurDao.getEmail());
        utilisateurEntity.setMotDePasse(utilisateurDao.getMotDePasse());
        utilisateurEntity.setRole(Role.valueOf(String.valueOf(utilisateurDao.getRole())));
        return utilisateurEntity;
    }

    private UtilisateurDao convertToUtilisateurDao(Utilisateur utilisateurEntity) {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        utilisateurDao.setUtilisateurId(utilisateurEntity.getUtilisateurID());
        utilisateurDao.setNom(utilisateurEntity.getNom());
        utilisateurDao.setEmail(utilisateurEntity.getEmail());
        utilisateurDao.setMotDePasse(utilisateurEntity.getMotDePasse());
        utilisateurDao.setRole(String.valueOf(utilisateurEntity.getRole()));
        return utilisateurDao;
    }
}
