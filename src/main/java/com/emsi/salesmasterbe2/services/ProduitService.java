package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProduitService {
    ProduitDao saveProduit(MultipartFile file,String nom,
                           String description,double prix,int quantiteEnStock) throws IOException;
    ProduitDao getProduitById(Long id);
    PagedResponse<ProduitDao> getAllProduits(int page, int size,String nom);
    ProduitDao updateProduct(Long id, ProduitDao produitDao);
    ProduitDao deleteProduit(Long id);
}
