package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.services.ProduitService;
import com.emsi.salesmasterbe2.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
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
    public ResponseEntity<PagedResponse<ProduitDao>> getAllProduits(@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                                                    @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PagedResponse<ProduitDao> produits = produitService.getAllProduits(page, size);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProduitDao> deleteProduit(@PathVariable Long id) {
        ProduitDao produitDao = produitService.deleteProduit(id);
        if (produitDao != null) {
            return new ResponseEntity<>(produitDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
