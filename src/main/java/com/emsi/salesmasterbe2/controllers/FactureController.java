package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.FactureDao;
import com.emsi.salesmasterbe2.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {

    private final FactureService factureService;

    @Autowired
    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @PostMapping
    public ResponseEntity<FactureDao> createFacture(@RequestBody FactureDao factureDao) {
        FactureDao createdFacture = factureService.saveFacture(factureDao);
        return new ResponseEntity<>(createdFacture, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDao> getFactureById(@PathVariable Long id) {
        FactureDao factureDao = factureService.getFactureById(id);
        if (factureDao != null) {
            return new ResponseEntity<>(factureDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<FactureDao>> getAllFactures() {
        List<FactureDao> factures = factureService.getAllFactures();
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        factureService.deleteFacture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
