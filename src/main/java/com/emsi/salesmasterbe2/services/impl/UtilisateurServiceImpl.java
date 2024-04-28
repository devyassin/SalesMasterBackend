package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.UtilisateurDao;
import com.emsi.salesmasterbe2.entities.Utilisateur;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.UtilisateurRepository;
import com.emsi.salesmasterbe2.services.UtilisateurService;
import com.emsi.salesmasterbe2.utils.ApiServiceUtils;
import com.emsi.salesmasterbe2.utils.AppConstants;
import com.emsi.salesmasterbe2.utils.AppUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private ApiServiceUtils apiServiceUtils;


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
        return apiServiceUtils.getAllEntities(page,size,utilisateurRepository, UtilisateurDao.class);
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
