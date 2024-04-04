package com.emsi.salesmasterbe2.Services;

import com.emsi.salesmasterbe2.Entities.Facture;
import com.emsi.salesmasterbe2.Repository.FactureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FactureService {


    private FactureRepository factureRepository;

    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    public Facture getFactureById(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }
}

