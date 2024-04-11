package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.services.LigneDeVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lignes-de-vente")
public class LigneDeVenteController {

    private final LigneDeVenteService ligneDeVenteService;

    @Autowired
    public LigneDeVenteController(LigneDeVenteService ligneDeVenteService) {
        this.ligneDeVenteService = ligneDeVenteService;
    }

    @PostMapping
    public ResponseEntity<LigneDeVenteDao> createLigneDeVente(@RequestBody LigneDeVenteDao ligneDeVenteDao) {
        LigneDeVenteDao createdLigneDeVente = ligneDeVenteService.saveLigneDeVente(ligneDeVenteDao);
        return new ResponseEntity<>(createdLigneDeVente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneDeVenteDao> getLigneDeVenteById(@PathVariable Long id) {
        LigneDeVenteDao ligneDeVenteDao = ligneDeVenteService.getLigneDeVenteById(id);
        if (ligneDeVenteDao != null) {
            return new ResponseEntity<>(ligneDeVenteDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<LigneDeVenteDao>> getAllLignesDeVente() {
        List<LigneDeVenteDao> lignesDeVente = ligneDeVenteService.getAllLignesDeVente();
        return new ResponseEntity<>(lignesDeVente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigneDeVente(@PathVariable Long id) {
        ligneDeVenteService.deleteLigneDeVente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
