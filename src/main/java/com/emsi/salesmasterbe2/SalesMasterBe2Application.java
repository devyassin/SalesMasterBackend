package com.emsi.salesmasterbe2;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.LigneDeVenteDao;
import com.emsi.salesmasterbe2.daos.ProduitDao;
import com.emsi.salesmasterbe2.daos.VenteDao;
import com.emsi.salesmasterbe2.entities.Role;
import com.emsi.salesmasterbe2.entities.Statut;
import com.emsi.salesmasterbe2.entities.Utilisateur;
import com.emsi.salesmasterbe2.repository.UtilisateurRepository;
import com.emsi.salesmasterbe2.services.ClientService;
import com.emsi.salesmasterbe2.services.LigneDeVenteService;
import com.emsi.salesmasterbe2.services.ProduitService;
import com.emsi.salesmasterbe2.services.VenteService;
import com.emsi.salesmasterbe2.services.impl.ClientServiceImpl;
import com.emsi.salesmasterbe2.utils.GenerateFakeData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@AllArgsConstructor
public class SalesMasterBe2Application {

    private GenerateFakeData generateFakeData;

    public static void main(String[] args) {
        SpringApplication.run(SalesMasterBe2Application.class, args);
    }

    @Bean
    CommandLineRunner start(UtilisateurRepository utilisateurRepositery
    , ClientService clientService
    , ProduitService produitService,
                            LigneDeVenteService ligneDeVenteService
    , VenteService venteService){
        return  args -> {
//            Utilisateur utilisateur=new Utilisateur();
//            utilisateur.setNom("yassin lamouadden");
//            utilisateur.setEmail("mouden529@gmail.com");
//            utilisateur.setMotDePasse("1264qsfqsfqf");
//            utilisateur.setRole(Role.ADMIN);
//            utilisateurRepositery.save(utilisateur);
//            utilisateur=new Utilisateur();
//            utilisateur.setNom("Oussama Naaman ");
//            utilisateur.setEmail("Naaman@gmail.com");
//            utilisateur.setMotDePasse("123azerty.");
//            utilisateur.setRole(Role.ADMIN);
//            utilisateurRepositery.save(utilisateur);


//            generateFakeData.generateFakeClients(95);
//            generateFakeData.generateFakeProducts(40);
//            generateFakeData.generateVentes();
//            generateFakeData.generateVentes(1);
//            VenteDao venteDao=new VenteDao();
//            venteDao.setDateVente(LocalDate.now());
//            venteDao.setStatut(Statut.NOUVELLE);
//            ClientDao clientDao= clientService.getClientById(2L);
//            venteDao.setClient(clientDao);
//            LigneDeVenteDao ligneDeVenteDao=new LigneDeVenteDao();
//            ligneDeVenteDao.setVente(venteDao);
//            ProduitDao produitDao=produitService.getProduitById(3L);
//            venteDao.setTotal(produitDao.getPrix()*3);
//            ligneDeVenteDao.setProduit(produitDao);
//            ligneDeVenteDao.setQuantite(4);
//            ligneDeVenteDao.setPrixUnitaire(produitDao.getPrix());
//            ligneDeVenteDao.setProduit(produitDao);
//            ligneDeVenteDao.setVente(venteDao);
//            ligneDeVenteService.saveLigneDeVente(ligneDeVenteDao);


        };
    }

}
