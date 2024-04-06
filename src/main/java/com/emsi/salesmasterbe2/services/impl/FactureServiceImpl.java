package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.entities.Facture;
import com.emsi.salesmasterbe2.repository.FactureRepository;
import com.emsi.salesmasterbe2.services.FactureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FactureServiceImpl implements FactureService {


    private FactureRepository factureRepository;

    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public Facture getFactureById(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }
}

