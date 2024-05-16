package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.services.ProduitService;
import com.emsi.salesmasterbe2.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/produits")
public class ProduitController {

    private final ProduitService produitService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProduitDao> createProduit(@RequestParam("imageFile") MultipartFile file,String nom,
                                                    String description,double prix,int quantiteEnStock) throws IOException {
        ProduitDao createdProduit = produitService.saveProduit(file,nom,description,prix,quantiteEnStock);
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
                                                                    @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
                                                                    @RequestParam(name = "name",required = false,defaultValue = AppConstants.DEFAULT_NAME_VALUE) String name) {
        PagedResponse<ProduitDao> produits = produitService.getAllProduits(page, size,name);
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

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDao> updateProduit(@PathVariable Long id, @RequestBody ProduitDao produitDao) {
        try {
            ProduitDao updatedProduct = produitService.updateProduct(id, produitDao);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
