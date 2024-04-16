package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.UtilisateurDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.utils.AppConstants;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDao saveUtilisateur(UtilisateurDao utilisateurDao);
    UtilisateurDao getUtilisateurById(Long id);
    PagedResponse<UtilisateurDao> getAllUtilisateurs(int page, int size);
    UtilisateurDao deleteUtilisateur(Long id);
}
