package com.emsi.salesmasterbe2.Services;

import com.emsi.salesmasterbe2.Entities.LigneDeVente;
import com.emsi.salesmasterbe2.Repository.LigneDeVenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LigneDeVenteService {


    private LigneDeVenteRepository ligneDeVenteRepository;

    public List<LigneDeVente> getAllLignesDeVente() {
        return ligneDeVenteRepository.findAll();
    }

    public LigneDeVente getLigneDeVenteById(Long id) {
        return ligneDeVenteRepository.findById(id).orElse(null);
    }

    public LigneDeVente saveLigneDeVente(LigneDeVente ligneDeVente) {
        return ligneDeVenteRepository.save(ligneDeVente);
    }

    public void deleteLigneDeVente(Long id) {
        ligneDeVenteRepository.deleteById(id);
    }
}

