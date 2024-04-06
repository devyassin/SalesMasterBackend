package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.entities.LigneDeVente;
import com.emsi.salesmasterbe2.repository.LigneDeVenteRepository;
import com.emsi.salesmasterbe2.services.LigneDeVenteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LigneDeVenteServiceImpl implements LigneDeVenteService {


    private LigneDeVenteRepository ligneDeVenteRepository;

    @Override
    public List<LigneDeVente> getAllLignesDeVente() {
        return ligneDeVenteRepository.findAll();
    }

    @Override
    public LigneDeVente getLigneDeVenteById(Long id) {
        return ligneDeVenteRepository.findById(id).orElse(null);
    }

    @Override
    public LigneDeVente saveLigneDeVente(LigneDeVente ligneDeVente) {
        return ligneDeVenteRepository.save(ligneDeVente);
    }

    @Override
    public void deleteLigneDeVente(Long id) {
        ligneDeVenteRepository.deleteById(id);
    }
}

