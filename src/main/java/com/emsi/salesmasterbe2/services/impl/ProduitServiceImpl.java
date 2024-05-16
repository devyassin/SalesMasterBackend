package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.entities.Client;
import com.emsi.salesmasterbe2.entities.Produit;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.ProduitRepository;
import com.emsi.salesmasterbe2.services.ProduitService;
import com.emsi.salesmasterbe2.utils.ApiServiceUtils;
import com.emsi.salesmasterbe2.utils.AppUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private ApiServiceUtils apiServiceUtils;


    @Override
    public ProduitDao saveProduit(MultipartFile file,String nom,
                                  String description,double prix,int quantiteEnStock) throws IOException {
        Path folderPAth= Paths.get(System.getProperty("user.home"),
                "sales-master-data","produits");
        if(!Files.exists(folderPAth)){
            Files.createDirectories(folderPAth);
        }
        String fileName= UUID.randomUUID().toString();
        System.out.println(fileName +"ffffffffff");
        Path filePath=Paths.get(System.getProperty("user.home"),"sales-master-data","produits",fileName+".png");
        Files.copy(file.getInputStream(),filePath);
      ProduitDao produitDao=  ProduitDao.builder().prix(prix).nom(nom).
                description(description).quantiteEnStock(quantiteEnStock).image(filePath.toUri().toString()).build();

        Produit produitEntity = ObjectMapperUtils.map(produitDao, Produit.class);
        produitEntity = produitRepository.save(produitEntity);
        return ObjectMapperUtils.map(produitEntity, ProduitDao.class);
    }

   

    @Override
    public ProduitDao getProduitById(Long id) {
        Optional<Produit> produitOptional = produitRepository.findById(id);
        if (produitOptional.isEmpty()) {
            throw new IllegalArgumentException("Produit with ID " + id + " not found");
        }
        return ObjectMapperUtils.map(produitOptional.get(), ProduitDao.class);
    }

    @Override
    public PagedResponse<ProduitDao> getAllProduits(int page, int size,String nom) {
        return apiServiceUtils.getAllEntities(page,size,nom,"produits",produitRepository, ProduitDao.class);
    }

    @Override
    public ProduitDao updateProduct(Long id, ProduitDao produitDao){
        Optional<Produit> produitOptional = produitRepository.findById(id);
        if (produitOptional.isPresent()) {
            Produit existingProduct = produitOptional.get();
            Produit newModifiedProduct= ObjectMapperUtils.map(produitDao, existingProduct);

            Produit updatedProduct = produitRepository.save(newModifiedProduct);

            return ObjectMapperUtils.map(updatedProduct, ProduitDao.class);
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
    }
    @Override
    public ProduitDao deleteProduit(Long id) {
        Optional<Produit> produitOptional = produitRepository.findById(id);
        if (produitOptional.isPresent()) {
            produitRepository.deleteById(id);
            return ObjectMapperUtils.map(produitOptional.get(), ProduitDao.class);
        } else {
            throw new IllegalArgumentException("Produit with ID " + id + " not found");
        }
    }
}
