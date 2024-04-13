package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.UtilisateurDao;
import com.emsi.salesmasterbe2.entities.Role;
import com.emsi.salesmasterbe2.entities.Utilisateur;
import com.emsi.salesmasterbe2.repository.UtilisateurRepository;
import com.emsi.salesmasterbe2.services.UtilisateurService;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
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
        Utilisateur utilisateurEntity = ObjectMapperUtils.map(utilisateurDao,Utilisateur.class);
        utilisateurEntity = utilisateurRepository.save(utilisateurEntity);
        return ObjectMapperUtils.map(utilisateurEntity,UtilisateurDao.class);
    }

    @Override
    public UtilisateurDao getUtilisateurById(Long id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);

        if (utilisateurOptional.isEmpty()) {
            throw new IllegalArgumentException("Utilisateur with ID " + id + " not found");
        }
        return ObjectMapperUtils.map(utilisateurOptional.get(), UtilisateurDao.class);
    }

    @Override
    public List<UtilisateurDao> getAllUtilisateurs() {
        List<Utilisateur> utilisateursList = utilisateurRepository.findAll();
        return ObjectMapperUtils.mapAll(utilisateursList,UtilisateurDao.class);
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

}
