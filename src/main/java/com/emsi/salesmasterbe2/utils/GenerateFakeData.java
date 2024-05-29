package com.emsi.salesmasterbe2.utils;

import com.emsi.salesmasterbe2.daos.*;
import com.emsi.salesmasterbe2.entities.Produit;
import com.emsi.salesmasterbe2.entities.Statut;
import com.emsi.salesmasterbe2.repository.ProduitRepository;
import com.emsi.salesmasterbe2.services.ProduitService;
import com.emsi.salesmasterbe2.services.impl.ClientServiceImpl;
import com.emsi.salesmasterbe2.services.impl.ProduitServiceImpl;
import com.emsi.salesmasterbe2.services.impl.VenteServiceImpl;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@AllArgsConstructor
@Service
public class GenerateFakeData {

    private  ClientServiceImpl clientService;
    private ProduitRepository produitRepository;
    private VenteServiceImpl venteService;
    private ProduitService produitService;
    public  void generateFakeClients(int numClients){
        Faker faker = new Faker(Locale.FRANCE);
        for (int i = 0; i < numClients; i++) {
            ClientDao clientDao = new ClientDao();
            clientDao.setNom(faker.name().fullName());
            clientDao.setAdresse(faker.address().fullAddress());
            clientDao.setEmail(faker.internet().emailAddress());
            clientDao.setTelephone(faker.phoneNumber().phoneNumber());
            clientService.saveClient(clientDao);
        }
    }

    public  void generateFakeProducts(int numProducts){
        Faker faker = new Faker(Locale.ENGLISH);
        for (int i = 0; i < numProducts; i++) {
            ProduitDao produitDao = new ProduitDao();
            produitDao.setNom(faker.commerce().productName());
            produitDao.setPrix(Double.parseDouble(faker.commerce().price()));
            produitDao.setDescription(faker.lorem().paragraph(50));
            produitDao.setQuantiteEnStock(faker.number().numberBetween(3,60));
            produitDao.setImage(faker.internet().image());
            Produit produitEntity = ObjectMapperUtils.map(produitDao, Produit.class);
            produitRepository.save(produitEntity);
        }
    }
    public  void generateVentes(){
        VenteDao venteDao=new VenteDao();
        venteDao.setStatut(Statut.NOUVELLE);
        venteDao.setClient(clientService.getClientById(2L));
        ProduitQauntiteDao produitQauntiteDao1=new ProduitQauntiteDao();
        produitQauntiteDao1.setProduit(produitService.getProduitById(2L));
        produitQauntiteDao1.setQuantite(5);
        ProduitQauntiteDao produitQauntiteDao2=new ProduitQauntiteDao();
        produitQauntiteDao2.setProduit(produitService.getProduitById(3L));
        produitQauntiteDao2.setQuantite(6);
        List<ProduitQauntiteDao> produitQauntiteDaos =new ArrayList<>();

        produitQauntiteDaos.add(produitQauntiteDao1);
        produitQauntiteDaos.add(produitQauntiteDao2);
        venteDao.setProduitQauntiteDao(produitQauntiteDaos);
        venteService.saveVente(venteDao);
    }
}
