package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.UtilisateurDao;
import com.emsi.salesmasterbe2.entities.Utilisateur;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.UtilisateurRepository;
import com.emsi.salesmasterbe2.services.UtilisateurService;
import com.emsi.salesmasterbe2.utils.AppConstants;
import com.emsi.salesmasterbe2.utils.AppUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
    public PagedResponse<UtilisateurDao> getAllUtilisateurs(int page,int size) {
        AppUtils.validatePageNumberAndSize(page,size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Utilisateur> utilisateursPage = utilisateurRepository.findAll(pageable);
        List<UtilisateurDao> utilisateurs=ObjectMapperUtils.mapAll(utilisateursPage.getContent(),
                UtilisateurDao.class);
        PagedResponse<UtilisateurDao> response=new PagedResponse<>(utilisateurs,
                page,size,utilisateursPage.getNumberOfElements(),utilisateursPage.getTotalPages());
        return response;
    }

    @Override
    public UtilisateurDao deleteUtilisateur(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isPresent()) {
            utilisateurRepository.deleteById(id);
            return ObjectMapperUtils.map(utilisateur.get(),UtilisateurDao.class);
        } else {
            throw new IllegalArgumentException("Utilisateur with ID " + id + " not found");
        }
    }
}
