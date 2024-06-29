package com.emsi.salesmasterbe2.utils;

import com.emsi.salesmasterbe2.daos.*;
import com.emsi.salesmasterbe2.entities.Produit;
import com.emsi.salesmasterbe2.entities.Statut;
import com.emsi.salesmasterbe2.payload.request.FakeVenteRequest;
import com.emsi.salesmasterbe2.repository.ClientRepository;
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
import java.util.*;


@AllArgsConstructor
@Service
public class GenerateFakeData {

    private  ClientServiceImpl clientService;
    private ProduitRepository produitRepository;
    private VenteServiceImpl venteService;
    private ProduitService produitService;
    private ClientRepository clientRepository;

    public  void generateFakeClients(int numClients){
        Faker faker = new Faker(Locale.FRANCE);
        for (int i = 0; i < numClients; i++) {
            ClientDao clientDao = new ClientDao();
            clientDao.setNom(faker.name().fullName());
            clientDao.setAdresse(faker.address().fullAddress());
            clientDao.setEmail(faker.internet().emailAddress());
            clientDao.setTelephone(faker.phoneNumber().phoneNumber());
            if(!clientRepository.existsClientByEmail(clientDao.getEmail())){
                clientService.saveClient(clientDao);
            }
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
        ArrayList<FakeVenteRequest> fakeVenteRequests=fakeVenteData();

        for (FakeVenteRequest request : fakeVenteRequests) {
            VenteDao venteDao = new VenteDao();
            venteDao.setStatut(getRandomStatut());
            venteDao.setClient(clientService.getClientById(request.getClientId()));

            List<ProduitQauntiteDao> produitQauntiteDaos = new ArrayList<>();
            long[] produitIds = request.getProductIds();
            int[] quantites = request.getQuantities();

            for (int i = 0; i < produitIds.length; i++) {
                ProduitQauntiteDao produitQauntiteDao = new ProduitQauntiteDao();
                produitQauntiteDao.setProduit(produitService.getProduitById(produitIds[i]));
                produitQauntiteDao.setQuantite(quantites[i]);
                produitQauntiteDaos.add(produitQauntiteDao);
            }

            venteDao.setProduitQauntiteDaos(produitQauntiteDaos);
            venteService.saveVente(venteDao);
        }
    }

    public ArrayList<FakeVenteRequest> fakeVenteData(){

        ArrayList<FakeVenteRequest> fakeVenteRequests = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            long clientId = random.nextInt(60) + 1;
            long[] idsProds = {random.nextInt(39) + 1, random.nextInt(39) + 1};
            int[] quantities = {random.nextInt(4) + 1, random.nextInt(4) + 1};
            FakeVenteRequest fakeVenteRequest = new FakeVenteRequest(clientId, idsProds, quantities);
            fakeVenteRequests.add(fakeVenteRequest);
        }

        return fakeVenteRequests;
    }

    private Statut getRandomStatut() {
        Statut[] statuses = Statut.values();
        Random random = new Random();
        return statuses[random.nextInt(statuses.length)];
    }
}
