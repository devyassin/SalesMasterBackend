package com.emsi.salesmasterbe2.utils;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.entities.Produit;
import com.emsi.salesmasterbe2.entities.Statut;
import com.emsi.salesmasterbe2.repository.ProduitRepository;
import com.emsi.salesmasterbe2.services.impl.ClientServiceImpl;
import com.emsi.salesmasterbe2.services.impl.ProduitServiceImpl;
import com.emsi.salesmasterbe2.services.impl.VenteServiceImpl;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;


@AllArgsConstructor
@Service
public class GenerateFakeData {

    private  ClientServiceImpl clientService;
    private ProduitRepository produitRepository;
    private VenteServiceImpl venteService;
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
//    public  void generateFakeVentes(int numVentes){
//        Faker faker = new Faker(Locale.ENGLISH);
//        for (int i = 0; i < numVentes; i++) {
//            VenteDao venteDao=new VenteDao();
//            venteDao.setDateVente(LocalDate.now());
//            venteDao.setStatut(Statut.NOUVELLE);
//            ClientDao clientDao= clientService.getClientById(2L);
//            venteDao.setClient(clientDao);
//            LigneDeVenteDao ligneDeVenteDao=new LigneDeVenteDao();
//
////            produitDao.setNom(faker.commerce().productName());
////            produitDao.setPrix(Double.parseDouble(faker.commerce().price()));
////            produitDao.setDescription(faker.lorem().paragraph(50));
////            produitDao.setQuantiteEnStock(faker.number().numberBetween(3,60));
////            produitDao.setImage(faker.internet().image());
////            Produit produitEntity = ObjectMapperUtils.map(produitDao, Produit.class);
////            venteService.saveVente(venteDao);
//        }
//    }
}
