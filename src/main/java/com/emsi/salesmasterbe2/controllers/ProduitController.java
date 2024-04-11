package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    private final ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public ResponseEntity<ProduitDao> createProduit(@RequestBody ProduitDao produitDao) {
        ProduitDao createdProduit = produitService.saveProduit(produitDao);
        return new ResponseEntity<>(createdProduit, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDao> getProduitById(@PathVariable Long id) {
        ProduitDao produitDao = produitService.getProduitById(id);
        if (produitDao != null) {
            return new ResponseEntity<>(produitDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProduitDao>> getAllProduits() {
        List<ProduitDao> produits = produitService.getAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
